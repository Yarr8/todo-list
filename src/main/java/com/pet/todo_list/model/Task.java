package com.pet.todo_list.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Task {
    private final UUID id;
    private final String title;
    private final String description;
    private final LocalDate dueDate;
    private final TaskStatus status;

    protected Task() {
        this.id = null;
        this.title = null;
        this.description = null;
        this.dueDate = null;
        this.status = null;
    }

    public static Task createNew(String title, String description, LocalDate dueDate) {
        return new Task(UUID.randomUUID(), title, description, dueDate, TaskStatus.TODO);
    }

    public Task withStatus(TaskStatus newStatus) {
        return new Task(this.id, this.title, this.description, this.dueDate, newStatus);
    }

    public Task withDueDate(LocalDate newDueDate) {
        return new Task(this.id, this.title, this.description, newDueDate, this.status);
    }
}
