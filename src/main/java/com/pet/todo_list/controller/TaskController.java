package com.pet.todo_list.controller;

import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import com.pet.todo_list.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    public Task addTask(String title, String description, LocalDate dueDate) {
        log.info("Creating task with title: {}", title);
        try {
            Task task = taskService.addTask(title, description, dueDate);
            log.info("Task created successfully with id: {}", task.getId());
            return task;
        } catch (Exception e) {
            log.error("Error creating task: {}", e.getMessage());
            return null;
        }
    }

    public Task editTask(UUID id, String title, String description, LocalDate dueDate, TaskStatus status) {
        log.info("Updating task with id: {}", id);
        try {
            Task task = taskService.editTask(id, title, description, dueDate, status);
            log.info("Task updated successfully with id: {}", task.getId());
            return task;
        } catch (Exception e) {
            log.error("Error updating task: {}", e.getMessage());
            return null;
        }
    }

    public List<Task> listAllTasks() {
        log.info("Returning all tasks");
        return taskService.listTasks();
    }

    public void deleteTask(UUID id) {
        log.info("Deleting task with id: {}", id);
        try {
            taskService.deleteTask(id);
            log.info("Task deleted successfully with id: {}", id);
        } catch (Exception e) {
            log.error("Error deleting task: {}", e.getMessage());
        }
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
