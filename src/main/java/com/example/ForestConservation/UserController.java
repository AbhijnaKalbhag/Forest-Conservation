package com.example.ForestConservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/Forest")
public class UserController {

    @Autowired
    private com.example.ForestConservation.UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload) {
        User user = userService.createuser(
                payload.get("name"),
                payload.get("age"),
                payload.get("gender"),
                payload.get("phone"),
                payload.get("email"),
                payload.get("state"),
                payload.get("district"),
                payload.get("password")
        );
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        User user = userService.loginUser(email, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
