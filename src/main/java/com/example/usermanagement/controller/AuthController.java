package com.example.usermanagement.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagement.dto.AuthRequest;
import com.example.usermanagement.dto.AuthResponse;
import com.example.usermanagement.dto.UserCreateRequest;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.security.CustomUserDetailsService;
import com.example.usermanagement.security.JwtUtil;
import com.example.usermanagement.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthenticationManager authManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil, UserService userService) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Validated @RequestBody AuthRequest req) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid credentials");
        }
        UserDetails ud = userDetailsService.loadUserByUsername(req.getEmail());
        String token = jwtUtil.generateToken(ud);
        return new AuthResponse(token);
    }

    // Optional: allow creation of users (ADMIN will be seeded anyway)
    @PostMapping("/register")
    public User register(@Validated @RequestBody UserCreateRequest req) {
        return userService.createUser(req);
    }
}
