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

    public Task addTask(final String title, final String description, final LocalDate dueDate) {
        final Task task = Task.createNew(title, description, dueDate);
        repository.save(task);
        return task;
    }

    public Task editTask(final UUID id, final String title, final String description,
                         final LocalDate dueDate, final TaskStatus status) {
        final Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        final Task.TaskBuilder builder = task.toBuilder();
        if (title != null) builder.title(title);
        if (description != null) builder.description(description);
        if (dueDate != null) builder.dueDate(dueDate);
        if (status != null) builder.status(status);
        final Task newTask = builder.build();
        repository.save(newTask);
        return newTask;
    }

    public void deleteTask(final UUID id) {
        repository.delete(id);
    }

    public List<Task> listTasks() {
        return repository.findAll();
    }

    public List<Task> filterByStatus(final TaskStatus status) {
        return null;
    }

    public List<Task> sortByDueDate() {
        return null;
    }

    public List<Task> sortByStatus() {
        return null;
    }
}
