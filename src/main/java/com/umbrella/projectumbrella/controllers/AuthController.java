package com.umbrella.projectumbrella.controllers;

import com.umbrella.projectumbrella.dto.*;
import com.umbrella.projectumbrella.entities.User;
import com.umbrella.projectumbrella.repositories.UserRepository;
import com.umbrella.projectumbrella.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    public JwtAuthResponseDTO loginUser(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        var user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No user with the given email is found"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        // create a cookie
        Cookie cookie = new Cookie("refreshToken", refreshToken);

        //add a cookie to the response
        response.addCookie(cookie);

        return new JwtAuthResponseDTO(jwt);
    }


    @GetMapping("/refresh")
    public ResponseEntity<RefreshTokenDTO> getRefreshToken(@CookieValue("refreshToken") String jwt ,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        if(jwt != null){
            var email = jwtService.extractUserName(jwt);
            UserDetails user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("No user with the given email is found"));
                    if(jwtService.isTokenValid(jwt,user)){
                        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
                        var accessToken = jwtService.generateToken(user);
                        Cookie cookie = new Cookie("refreshToken", refreshToken);


                        //add a cookie to the response
                        httpServletResponse.addCookie(cookie);
                        return new ResponseEntity<>(new RefreshTokenDTO(accessToken), HttpStatus.OK);
                    }
                    else
                        return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response,
                                         @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        if (refreshToken != null) {
            // Expire the refresh token cookie by setting its max age to 0
            Cookie cookie = new Cookie("refreshToken", null);
            response.addCookie(cookie);
        }

        // Clear the authentication token by setting the Authorization header to null
        response.setHeader("Authorization", null);

        return ResponseEntity.ok("Logged out successfully");
    }
}
