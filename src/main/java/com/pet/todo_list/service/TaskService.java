package com.pet.todo_list.service;

import com.pet.todo_list.exception.TaskNotFoundException;
import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import com.pet.todo_list.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public Task addTask(String title, String description, LocalDate dueDate) {
        Task task = Task.createNew(title, description, dueDate);
        repository.save(task);
        return task;
    }

    public Task editTask(UUID id, String title, String description, LocalDate dueDate) {
        Task task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        Task.TaskBuilder builder = task.toBuilder();
        if (title != null) builder.title(title);
        if (description != null) builder.description(description);
        if (dueDate != null) builder.dueDate(dueDate);
        Task newTask = builder.build();
        repository.save(newTask);
        return newTask;
    }

    public Optional<Task> updateStatus(UUID id, TaskStatus status) {
        return Optional.empty();
    }

    public boolean deleteTask(UUID id) {
        return false;
    }

    public List<Task> listTasks() {
        return repository.findAll();
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
