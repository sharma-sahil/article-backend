package sharma.sahil.learning.java.article.backend.api;

import sharma.sahil.learning.java.article.backend.dto.CreateUserRequest;
import sharma.sahil.learning.java.article.backend.dto.UserResponse;
import sharma.sahil.learning.java.article.backend.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/public/user")
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        return this.userManagementService.createUser(createUserRequest);
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
