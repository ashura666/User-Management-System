package com.example.usermanagement.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagement.dto.TaskRequest;
import com.example.usermanagement.entity.Task;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.TaskService;
import com.example.usermanagement.service.UserService;

@RestController
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
	private final UserService userService;
    private final TaskService taskService;

    public ManagerController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/users")
    public List<User> allUsers() { return userService.getAll(); }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody TaskRequest req) { return taskService.create(req); }

    @GetMapping("/tasks")
    public List<Task> allTasks() { return taskService.allTasks(); }
}
