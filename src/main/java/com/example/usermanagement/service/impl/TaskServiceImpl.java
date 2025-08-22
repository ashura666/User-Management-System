package com.example.usermanagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usermanagement.dto.TaskRequest;
import com.example.usermanagement.entity.Task;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.TaskRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public TaskServiceImpl(TaskRepository taskRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Task create(TaskRequest req) {
        User assignee = userRepo.findById(req.getAssigneeId())
                .orElseThrow(() -> new RuntimeException("Assignee not found"));
        Task t = new Task(req.getTitle(), req.getDescription(), assignee);
        return taskRepo.save(t);
    }

    @Override
    public List<Task> tasksForUser(Long userId) {
        return taskRepo.findByAssignee_Id(userId);
    }

    @Override
    public List<Task> allTasks() { return taskRepo.findAll(); }
}
