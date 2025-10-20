package com.pet.todo_list.cli;

import com.pet.todo_list.dto.CommandDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class CLIRunner implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {
    private final CommandParser parser;
    private final CommandHandler handler;
    private final ExceptionHandler exceptionHandler;

    private boolean running = true;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        running = false;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Todo-list application started");
        System.out.println("Enter command, or HELP to list all available commands");
        while (running) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            try {
                CommandDto commandDto = parser.parse(line);
                handler.handle(commandDto);
            } catch (Exception e) {
                exceptionHandler.handle(e);
            }
        }
    }
}
