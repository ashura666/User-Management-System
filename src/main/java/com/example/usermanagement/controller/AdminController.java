package com.example.usermanagement.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagement.dto.AssignRoleRequest;
import com.example.usermanagement.dto.UserCreateRequest;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	 private final UserService userService;
	    public AdminController(UserService userService) { this.userService = userService; }

	    @PostMapping("/users")
	    public User create(@Validated @RequestBody UserCreateRequest req) { return userService.createUser(req); }

	    @GetMapping("/users")
	    public List<User> all() { return userService.getAll(); }

	    @GetMapping("/users/{id}")
	    public User one(@PathVariable Long id) { return userService.getById(id); }

	    @PutMapping("/users/{id}")
	    public User update(@PathVariable Long id, @RequestBody UserCreateRequest req) { return userService.updateUser(id, req); }

	    @DeleteMapping("/users/{id}")
	    public void delete(@PathVariable Long id) { userService.deleteUser(id); }

	    @PostMapping("/users/{id}/roles")
	    public User assignRole(@PathVariable Long id, @Validated @RequestBody AssignRoleRequest req) {
	        return userService.assignRole(id, req.getRoleName());
	    }
}