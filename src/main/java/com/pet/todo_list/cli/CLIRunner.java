package com.pet.todo_list.cli;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class CLIRunner implements CommandLineRunner{
    private boolean running = true;

    @Override
    public void run(String... args) throws Exception {
        log.info("Todo-list application started");
        System.out.println("Enter command, or HELP to list all available commands");
        while (running) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("EXIT"))
                running = false;
        }
    }
}
