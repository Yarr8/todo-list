package com.pet.todo_list.service;

import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    public Task addTask(String title, String description, LocalDate dueDate) {
        return null;
    }

    public Optional<Task> editTask(UUID id, String title, String description, LocalDate dueDate) {
        return Optional.empty();
    }

    public Optional<Task> updateStatus(UUID id, TaskStatus status) {
        return Optional.empty();
    }

    public boolean deleteTask(UUID id) {
        return false;
    }

    public List<Task> listTasks() {
        return null;
    }

    public List<Task> filterByStatus(TaskStatus status) {
        return null;
    }

    public List<Task> sortByDueDate() {
        return null;
    }

    public List<Task> sortByStatus() {
        return null;
    }
}
