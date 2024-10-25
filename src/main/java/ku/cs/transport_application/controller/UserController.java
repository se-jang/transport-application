package ku.cs.transport_application.controller;

import ku.cs.transport_application.service.OrderService;
import ku.cs.transport_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserDetail(@PathVariable UUID userId, OrderService orderService) {
        return ResponseEntity.ok((orderService.getOrdersByUser(userId)));
    }
}
