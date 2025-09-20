package main.java.com.library.controllers;

import jakarta.servlet.http.HttpSession;
import main.java.com.library.data.models.User;
import main.java.com.library.data.repository.UserRepository;
import main.java.com.library.dto.User.UserDto;
import main.java.com.library.dto.User.request.LoginRequestDto;
import main.java.com.library.dto.User.request.RegisterRequestDto;
import main.java.com.library.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto userData, HttpSession session) {
        UserDto isRegistered = userServices.registerUser(userData
        );
        if (isRegistered != null) {
            session.setAttribute("user", userData.getUserName());
            return ResponseEntity.ok("User registered and session started.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginDto, HttpSession session) {
        UserDto isAuthenticated = userServices.loginUser(loginDto);
        User user = userRepository.findByUsername(loginDto.getUserName());
        if (isAuthenticated != null) {
            session.setAttribute("user", user);
            return ResponseEntity.ok("Login successful. Session started.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Session ended. Logged out.");
    }
}

