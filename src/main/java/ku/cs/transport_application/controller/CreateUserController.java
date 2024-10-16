package ku.cs.transport_application.controller;

import jakarta.validation.Valid;
import ku.cs.transport_application.request.CreateUserRequest;
import ku.cs.transport_application.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateUserController {

    @Autowired
    private CreateUserService createUserService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest user) {
        if (!createUserService.isUsernameAvailable(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username not available");
        }
        createUserService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
}
