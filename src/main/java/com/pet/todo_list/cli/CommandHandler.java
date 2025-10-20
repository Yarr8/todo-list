package com.pet.todo_list.cli;

import com.pet.todo_list.controller.TaskController;
import com.pet.todo_list.dto.CommandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandHandler {
    private final TaskController controller;
    private final ConfigurableApplicationContext context;

    public void handle(CommandDto command) {
        switch (command.getCommand()) {
            case EXIT -> handleExit();
        }
    }

    private void handleExit() {
        System.out.println("Exiting application...");
        context.close();
    }
}
