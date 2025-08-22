package com.example.usermanagement.service.impl;


import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usermanagement.dto.UserCreateRequest;
import com.example.usermanagement.entity.Role;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.RoleRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @Override
    public User createUser(UserCreateRequest req) {
        if (userRepo.existsByEmail(req.getEmail()))
            throw new RuntimeException("Duplicate user email");
        User u = new User(req.getName(), req.getEmail(), encoder.encode(req.getPassword()));
        return userRepo.save(u);
    }

    @Override
    public User updateUser(Long id, UserCreateRequest req) {
        User u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (req.getName() != null) u.setName(req.getName());
        if (req.getEmail() != null) u.setEmail(req.getEmail());
        if (req.getPassword() != null) u.setPassword(encoder.encode(req.getPassword()));
        return userRepo.save(u);
    }

    @Override
    public void deleteUser(Long id) { userRepo.deleteById(id); }

    @Override
    public List<User> getAll() { return userRepo.findAll(); }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User assignRole(Long userId, String roleName) {
        User u = getById(userId);
        Role r = roleRepo.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
        u.getRoles().add(r);
        return userRepo.save(u);
    }

    @Override
    public User getByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
