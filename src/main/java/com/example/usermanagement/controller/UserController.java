package com.example.usermanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagement.entity.Task;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.TaskService;
import com.example.usermanagement.service.UserService;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
	 private final UserService userService;
	    private final TaskService taskService;

	    public UserController(UserService userService, TaskService taskService) {
	        this.userService = userService;
	        this.taskService = taskService;
	    }

	    @GetMapping("/me")
	    public User me(Principal principal) {
	        return userService.getByEmail(principal.getName());
	    }

	    @GetMapping("/tasks")
	    public List<Task> myTasks(Principal principal) {
	        User me = userService.getByEmail(principal.getName());
	        return taskService.tasksForUser(me.getId());
	    }
}
