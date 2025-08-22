package com.example.usermanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.usermanagement.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee_Id(Long userId);
}