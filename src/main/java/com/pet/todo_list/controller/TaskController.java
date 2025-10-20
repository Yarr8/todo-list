package com.pet.todo_list.controller;

import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import com.pet.todo_list.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    public Task addTask(String title, String description, LocalDate dueDate) {
        return taskService.addTask(title, description, dueDate);
    }

    public List<Task> listAllTasks() {
        return taskService.listTasks();
    }

    public Optional<Task> editTask(UUID id, String title, String description, LocalDate dueDate) {
        return taskService.editTask(id, title, description, dueDate);
    }

    public Optional<Task> updateStatus(UUID id, TaskStatus status) {
        return taskService.updateStatus(id, status);
    }

    public boolean deleteTask(UUID id) {
        return taskService.deleteTask(id);
    }

    public List<Task> filterByStatus(TaskStatus status) {
        return taskService.filterByStatus(status);
    }

    public List<Task> sortByDueDate() {
        return taskService.sortByDueDate();
    }

    public List<Task> sortByStatus() {
        return taskService.sortByStatus();
    }
}
