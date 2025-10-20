package com.pet.todo_list.dto;

import com.pet.todo_list.cli.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class CommandDto {
    private final Command command;
    private final Map<String, String> args;
}
