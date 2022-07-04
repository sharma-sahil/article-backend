package sharma.sahil.learning.java.article.backend.security;

import sharma.sahil.learning.java.article.backend.entity.UserEntity;
import sharma.sahil.learning.java.article.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEnt = userRepository.findByUsername(username);
        if (!userEnt.isPresent()) {
            throw new UsernameNotFoundException(username + " not found");
        }
        UserEntity userEntity = userEnt.get();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        UserPrincipal principal = new UserPrincipal(userEntity, authorities);

        return principal;
    }
}
