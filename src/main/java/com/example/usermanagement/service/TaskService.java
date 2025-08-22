package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.dto.TaskRequest;
import com.example.usermanagement.entity.Task;

public interface TaskService {
	Task create(TaskRequest req);
    List<Task> tasksForUser(Long userId);
    List<Task> allTasks();
}
