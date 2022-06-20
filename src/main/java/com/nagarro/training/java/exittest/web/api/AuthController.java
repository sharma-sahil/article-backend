package com.nagarro.training.java.exittest.web.api;

import com.nagarro.training.java.exittest.dto.AuthResponse;
import com.nagarro.training.java.exittest.security.JwtTokenUtil;
import com.nagarro.training.java.exittest.security.JwtUserDetailsService;
import com.nagarro.training.java.exittest.dto.LoginRequest;
import com.nagarro.training.java.exittest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return this.authService.loginUser(loginRequest);
    }
}
