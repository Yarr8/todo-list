package com.pet.todo_list.cli;

import com.pet.todo_list.dto.CommandDto;
import com.pet.todo_list.exception.InvalidCommandException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandParser {
    public CommandDto parse(String input) {
        final String[] tokens = input.trim().split("\\s+");
        final String commandStr = tokens[0].toUpperCase();
        Command command;
        try {
            command = Command.valueOf(commandStr);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidCommandException(String.format("Unknown command: %s\n", commandStr));
        }

        final Map<String, String> args = new HashMap<>();
        for (int i = 1; i < tokens.length; i++) {
            String[] pair = tokens[i].split("=", 2);
            if (pair.length == 2) {
                args.put(pair[0], pair[1]);
            }
        }

        return new CommandDto(command, args);
    }
}
