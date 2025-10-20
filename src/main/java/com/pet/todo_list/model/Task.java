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

    public TaskBuilder toBuilder() {
        return new TaskBuilder(this);
    }

    public static class TaskBuilder {
        private UUID id;
        private String title;
        private String description;
        private LocalDate dueDate;
        private TaskStatus status;

        public TaskBuilder() {
        }

        private TaskBuilder(Task task) {
            this.id = task.id;
            this.title = task.title;
            this.description = task.description;
            this.dueDate = task.dueDate;
            this.status = task.status;
        }

        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskBuilder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Task build() {
            return new Task(id, title, description, dueDate, status);
        }
    }
}
