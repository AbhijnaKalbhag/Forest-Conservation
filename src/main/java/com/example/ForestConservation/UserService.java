package com.example.ForestConservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private com.example.ForestConservation.UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createuser(String name, String age, String gender, String phone, String email, String state, String district,  String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, age, gender, phone, email, state, district, encodedPassword);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
