package com.pet.todo_list.cli;

import com.pet.todo_list.controller.TaskController;
import com.pet.todo_list.dto.CommandDto;
import com.pet.todo_list.exception.InvalidCommandException;
import com.pet.todo_list.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommandHandler {
    private final TaskController controller;
    private final ConfigurableApplicationContext context;

    public void handle(CommandDto command) {
        switch (command.getCommand()) {
            case EXIT -> handleExit();
            case ADD -> handleAdd(command.getArgs());
            case EDIT -> handleEdit(command.getArgs());
            case LIST -> handleList();
        }
    }

    private void handleExit() {
        System.out.println("Exiting application...");
        context.close();
    }

    private void handleAdd(Map<String, String> args) {
        String title = args.get("name");
        String description = args.get("desc");
        String dateStr = args.get("date");
        if (title == null || description == null || dateStr == null) {
            throw new InvalidCommandException("Arguments name=, desc= and date= should be specified");
        }

        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format: " + dateStr);
        }
        controller.addTask(title, description, dueDate);
    }

    private void handleEdit(Map<String, String> args) {
        String idStr = args.get("id");
        if (idStr == null) {
            throw new InvalidCommandException("Task ID argument id= should be specified");
        }
        String title = args.get("name");
        String description = args.get("desc");
        String dateStr = args.get("date");

        LocalDate dueDate;
        if (dateStr == null) {
            dueDate = null;
        } else {
            try {
                dueDate = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException("Invalid date format: " + dateStr);
            }
        }

        UUID id;
        try {
            id = UUID.fromString(idStr);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidCommandException("Invalid ID format: " + idStr);
        }

        controller.editTask(id, title, description, dueDate);
    }

    private void handleList() {
        for (Task task : controller.listAllTasks()) {
            System.out.printf(
                    "id: %s, name: %s, description: %s, due date: %s, status: %s\n",
                    task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus()
            );
        }
    }
}
