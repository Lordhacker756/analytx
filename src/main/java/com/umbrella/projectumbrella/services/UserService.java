package com.umbrella.projectumbrella.services;

import com.umbrella.projectumbrella.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;

    public UserDetailsService userDetailsService(){

        return username -> userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("A user with the provided username doesnt exist" + username));

    }

}
