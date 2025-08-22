package com.example.usermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=150)
    private String title;

    @Column(length=1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status = TaskStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    public Task() {}
    public Task(String title, String description, User assignee) {
        this.title = title; this.description = description; this.assignee = assignee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public User getAssignee() { return assignee; }
    public void setAssignee(User assignee) { this.assignee = assignee; }
}
