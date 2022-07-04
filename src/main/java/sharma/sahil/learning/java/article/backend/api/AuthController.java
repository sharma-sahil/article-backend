package sharma.sahil.learning.java.article.backend.api;

import sharma.sahil.learning.java.article.backend.dto.AuthResponse;
import sharma.sahil.learning.java.article.backend.dto.LoginRequest;
import sharma.sahil.learning.java.article.backend.security.JwtTokenUtil;
import sharma.sahil.learning.java.article.backend.security.JwtUserDetailsService;
import sharma.sahil.learning.java.article.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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
