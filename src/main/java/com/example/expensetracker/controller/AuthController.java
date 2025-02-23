package com.example.expensetracker.controller;

import com.example.expensetracker.dto.UserDto;
import com.example.expensetracker.dto.UserMapper;
import com.example.expensetracker.model.User;
import com.example.expensetracker.security.CustomUserDetails;
import com.example.expensetracker.security.JwtTokenProvider;
import com.example.expensetracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerNewUserAccount(user);
        return ResponseEntity.ok(UserMapper.toDto(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User login) {
        return userService.findUserByUsername(login.getUsername())
                .filter(user -> passwordEncoder.matches(login.getPassword(), user.getPassword()))
                .map(user -> ResponseEntity.ok(jwtTokenProvider.generateToken(new CustomUserDetails(user))))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}