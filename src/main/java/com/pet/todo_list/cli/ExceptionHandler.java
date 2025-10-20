package com.pet.todo_list.cli;

import com.pet.todo_list.exception.InvalidCommandException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler {
    public void handle(Exception e) {
        if (e instanceof InvalidCommandException ice) {
            System.out.println("Command exception: " + ice.getMessage());
        } else {
            System.out.println("Unhandled exception: ");
            e.printStackTrace(System.err);
        }
    }
}
