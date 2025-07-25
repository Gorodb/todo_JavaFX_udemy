package com.rvakazov.todoapp.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TaskDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private LocalDateTime dateAdded;
    private String status;
    private List<String> comments;
    private String id;

    public TaskDTO(String title, String description, LocalDateTime dateAdded, String status) {
        this.title = title;
        this.description = description;
        this.dateAdded = dateAdded;
        this.status = status;
        this.comments = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object taskObj) {
        if (this == taskObj) return true;
        if (!(taskObj instanceof TaskDTO task)) return false;
        return Objects.equals(id, task.id);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
