package com.pet.todo_list.repository;

import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private final Map<UUID, Task> tasks = new HashMap<>();

    @Override
    public List<Task> findAll() {
        return tasks.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void delete(UUID id) {
        tasks.remove(id);
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return tasks.values()
                .stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }
}
