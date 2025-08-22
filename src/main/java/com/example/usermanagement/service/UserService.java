package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.dto.UserCreateRequest;
import com.example.usermanagement.entity.User;

public interface UserService {
	User createUser(UserCreateRequest req);
    User updateUser(Long id, UserCreateRequest req);
    void deleteUser(Long id);
    List<User> getAll();
    User getById(Long id);
    User assignRole(Long userId, String roleName);
    User getByEmail(String email);
}
