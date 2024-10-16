package ku.cs.transport_application.controller;

import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.request.LoginRequest;
import ku.cs.transport_application.response.JwtResponse;
import ku.cs.transport_application.service.JwtService;
import ku.cs.transport_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.findByUsername(request.getUsername());
        System.out.println("Login request: " + request);
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

}
