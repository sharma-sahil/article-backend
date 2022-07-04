package com.nagarro.training.java.exittest.service;

import com.nagarro.training.java.exittest.dto.AuthResponse;
import com.nagarro.training.java.exittest.dto.LoginRequest;
import com.nagarro.training.java.exittest.entity.UserEntity;
import com.nagarro.training.java.exittest.exception.CustomException;
import com.nagarro.training.java.exittest.repository.UserRepository;
import com.nagarro.training.java.exittest.security.JwtTokenUtil;
import com.nagarro.training.java.exittest.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthResponse loginUser(LoginRequest loginRequest) {

        UserEntity userEntity = this.userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new CustomException("Invalid user credentials passed in the request. Please check credentials", "invalid.credentials"));


        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        return authResponse;
    }
}
