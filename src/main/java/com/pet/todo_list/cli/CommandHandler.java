package com.pet.todo_list.cli;

import com.pet.todo_list.controller.TaskController;
import com.pet.todo_list.dto.CommandDto;
import com.pet.todo_list.exception.InvalidCommandException;
import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommandHandler {
    private final TaskController controller;
    private final ConfigurableApplicationContext context;

    public void handle(final CommandDto command) {
        switch (command.getCommand()) {
            case EXIT -> handleExit();
            case ADD -> handleAdd(command.getArgs());
            case EDIT -> handleEdit(command.getArgs());
            case LIST -> handleList();
            case DELETE -> handleDelete(command.getArgs());
            case FILTER -> handleFilter(command.getArgs());
        }
    }

    private void printTasksList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Empty");
        }

        for (Task task : tasks) {
            System.out.printf(
                    "id: %s, name: %s, description: %s, due date: %s, status: %s\n",
                    task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus()
            );
        }
    }

    private void handleExit() {
        System.out.println("Exiting application...");
        context.close();
    }

    private void handleAdd(final Map<String, String> args) {
        final String title = args.get("name");
        final String description = args.get("desc");
        final String dateStr = args.get("date");
        if (title == null || description == null || dateStr == null) {
            throw new InvalidCommandException("Arguments name=, desc= and date= should be specified");
        }

        final LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format: " + dateStr);
        }

        controller.addTask(title, description, dueDate);
    }

    private void handleEdit(final Map<String, String> args) {
        final String idStr = args.get("id");
        if (idStr == null) {
            throw new InvalidCommandException("Task ID argument id= should be specified");
        }
        final String title = args.get("name");
        final String description = args.get("desc");
        final String dateStr = args.get("date");
        final String statusStr = args.get("status");

        final LocalDate dueDate;
        if (dateStr == null) {
            dueDate = null;
        } else {
            try {
                dueDate = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException("Invalid date format: " + dateStr);
            }
        }

        TaskStatus status;
        if (statusStr == null) {
            status = null;
        } else {
            try {
                status = TaskStatus.valueOf(statusStr);
            } catch (IllegalArgumentException illegalArgumentException) {
                throw new InvalidCommandException(String.format("Unknown status: %s\n", statusStr));
            }
        }

        final UUID id;
        try {
            id = UUID.fromString(idStr);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidCommandException("Invalid ID format: " + idStr);
        }

        controller.editTask(id, title, description, dueDate, status);
    }

    private void handleList() {
        printTasksList(controller.listAllTasks());
    }

    private void handleDelete(final Map<String, String> args) {
        final String idStr = args.get("id");
        if (idStr == null) {
            throw new InvalidCommandException("Task ID argument id= should be specified");
        }

        final UUID id;
        try {
            id = UUID.fromString(idStr);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidCommandException("Invalid ID format: " + idStr);
        }

        controller.deleteTask(id);
    }

    private void handleFilter(final Map<String, String> args) {
        final String statusStr = args.get("status");
        if (statusStr == null) {
            throw new InvalidCommandException("Task status argument status= should be specified");
        }

        final TaskStatus status;
        try {
            status = TaskStatus.valueOf(statusStr);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidCommandException(String.format("Unknown status: %s\n", statusStr));
        }

        List<Task> tasks = controller.filterByStatus(status);
        printTasksList(tasks);
    }
}
