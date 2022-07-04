package sharma.sahil.learning.java.article.backend.service;

import sharma.sahil.learning.java.article.backend.dto.AuthResponse;
import sharma.sahil.learning.java.article.backend.dto.LoginRequest;
import sharma.sahil.learning.java.article.backend.entity.UserEntity;
import sharma.sahil.learning.java.article.backend.exception.CustomException;
import sharma.sahil.learning.java.article.backend.repository.UserRepository;
import sharma.sahil.learning.java.article.backend.security.JwtTokenUtil;
import sharma.sahil.learning.java.article.backend.security.JwtUserDetailsService;
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
