package sharma.sahil.learning.java.article.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import sharma.sahil.learning.java.article.backend.dto.CreateUserRequest;
import sharma.sahil.learning.java.article.backend.dto.UserResponse;
import sharma.sahil.learning.java.article.backend.entity.UserEntity;
import sharma.sahil.learning.java.article.backend.exception.CustomException;
import sharma.sahil.learning.java.article.backend.mapper.UserMapper;
import sharma.sahil.learning.java.article.backend.repository.UserRepository;
import sharma.sahil.learning.java.article.backend.security.UserPrincipal;
import sharma.sahil.learning.java.article.backend.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementService {

    private static final Logger log = LoggerFactory.getLogger(UserManagementService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(CreateUserRequest request) {

        log.info("Request received to create a new user with username : {}", request.getUsername());

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.error("Password and confirm password does not match with each other");
            throw new CustomException("Password and confirm password must match with each other", "invalid.password");
        }

        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            log.error("Request received to create a new user with username : {}", request.getUsername());
            throw new CustomException("A user with same username already exist. Please try another username",
                    "invalid.username");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(this.passwordEncoder.encode(request.getPassword()));
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity = this.userRepository.save(userEntity);
        return this.userMapper.convertToDto(userEntity);
    }


    public UserResponse getUser(Long userId) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(userId);
        if (!optionalUserEntity.isPresent()) {
            throw new CustomException("User not found with ID : " + userId, "invalid.user.id");
        }
        return this.userMapper.convertToDto(optionalUserEntity.get());
    }

    public UserResponse getLoggedInUserDetails() {
        UserPrincipal userPrincipal = SecurityUtils.getUserPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(userPrincipal.getUsername()).get();
        return this.userMapper.convertToDto(userEntity);
    }
}
