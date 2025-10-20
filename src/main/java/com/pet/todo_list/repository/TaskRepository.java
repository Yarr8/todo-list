package com.pet.todo_list.repository;

import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository {
    List<Task> findAll();

    Optional<Task> findById(UUID id);

    void save(Task task);

    void delete(UUID id);

    List<Task> findByStatus(TaskStatus status);
}
