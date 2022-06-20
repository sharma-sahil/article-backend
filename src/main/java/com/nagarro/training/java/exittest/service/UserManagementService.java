package com.nagarro.training.java.exittest.service;

import com.nagarro.training.java.exittest.dto.UserResponse;
import com.nagarro.training.java.exittest.entity.UserEntity;
import com.nagarro.training.java.exittest.exception.CustomException;
import com.nagarro.training.java.exittest.mapper.UserMapper;
import com.nagarro.training.java.exittest.repository.UserRepository;
import com.nagarro.training.java.exittest.security.UserPrincipal;
import com.nagarro.training.java.exittest.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserEntity createUser(UserEntity user) {
        return this.userRepository.save(user);
    }


    public UserResponse getUser(Long userId) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(userId);
        if (!optionalUserEntity.isPresent()) {
            throw new CustomException("User not found with ID : " + userId, "invalid.userId");
        }
        return this.userMapper.convertToDto(optionalUserEntity.get());
    }

    public UserResponse getLoggedInUserDetails() {
        UserPrincipal userPrincipal = SecurityUtils.getUserPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(userPrincipal.getUsername()).get();
        return this.userMapper.convertToDto(userEntity);
    }
}
