package com.umbrella.projectumbrella.controllers;

import com.umbrella.projectumbrella.dto.JwtAuthResponseDTO;
import com.umbrella.projectumbrella.dto.LoginDTO;
import com.umbrella.projectumbrella.dto.RegisterDTO;
import com.umbrella.projectumbrella.dto.Role;
import com.umbrella.projectumbrella.entities.User;
import com.umbrella.projectumbrella.repositories.UserRepository;
import com.umbrella.projectumbrella.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("/test")
    public Optional<User> test(){
        return userRepository.findByEmail("utkarsh@imf.co");
    }


    @PostMapping("/register")
    public RegisterDTO registerUser(@RequestBody RegisterDTO registerDTO) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setRole(Role.USER);

        User res = userRepository.save(user);

        return new RegisterDTO( res.getPassword(),res.getEmail() , res.getRole());
    }

    @PostMapping("/register-admin")
    public RegisterDTO registerAdmin(@RequestBody RegisterDTO registerDTO) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setRole(Role.ADMIN);

        System.out.println("ENCODED USER INFO SENT FOR STORE::"+ user.toString());

        User res = userRepository.save(user);

        return new RegisterDTO(res.getPassword(),res.getEmail() , res.getRole());
    }

    @PostMapping("/login")
    public JwtAuthResponseDTO loginUser(@RequestBody LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        var user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No user with the given email is found"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        return new JwtAuthResponseDTO(jwt, refreshToken);
    }
}
