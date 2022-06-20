package com.nagarro.training.java.exittest.web.api;

import com.nagarro.training.java.exittest.dto.UserResponse;
import com.nagarro.training.java.exittest.entity.UserEntity;
import com.nagarro.training.java.exittest.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/public/user")
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return this.userManagementService.createUser(userEntity);
    }

    @GetMapping("/user/{id}")
    public UserResponse getUser(@PathVariable("id") Long userId) {
        return this.userManagementService.getUser(userId);
    }

    @GetMapping("/user/profile")
    public UserResponse getUserDetails() {
        return this.userManagementService.getLoggedInUserDetails();
    }

}
