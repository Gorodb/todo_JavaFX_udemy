package com.rvakazov.todoapp.managers;

import com.rvakazov.todoapp.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<TaskDTO> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void addTask(TaskDTO task) {
        tasks.add(task);
    }

    public void removeTask(TaskDTO task) {
        tasks.remove(task);
    }
}
