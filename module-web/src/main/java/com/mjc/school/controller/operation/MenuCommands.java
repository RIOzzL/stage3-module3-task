package com.mjc.school.controller.operation;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.operation.commandImpl.*;
import com.mjc.school.controller.utils.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuCommands {

    private List<Command> commands;

    @Autowired
    public MenuCommands(List<Command> commands) {
        this.commands = commands;
    }

    @CommandHandler(0)
    public void printMenu() {
        System.out.println("Enter the number of operation:");
        for (Operations value : Operations.values()) {
            System.out.println(value.getOperationNumber() + " - " + value.getOperationDescription());
        }
    }

    @CommandHandler(1)
    public void printAllNews() {
        commands.stream().filter(command -> command instanceof GetAllNewsOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(2)
    public void printAllAuthors() {
        commands.stream().filter(command -> command instanceof GetAllAuthorsOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(3)
    public void printNewsByID() {
        commands.stream().filter(command -> command instanceof GetNewsByIdOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(4)
    public void printAuthorById() {
        commands.stream().filter(command -> command instanceof GetAuthorByIdOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(5)
    public void createNews() {
        commands.stream().filter(command -> command instanceof CreateNewsOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(6)
    public void createAuthor() {
        commands.stream().filter(command -> command instanceof CreateAuthorOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(7)
    public void updateNews() {
        commands.stream().filter(command -> command instanceof UpdateNewsOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(8)
    public void updateAuthor() {
        commands.stream().filter(command -> command instanceof UpdateAuthorOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(9)
    public void deleteNewsByID() {
        commands.stream().filter(command -> command instanceof DeleteNewsByIdOperation)
                .findFirst().get()
                .execute();
    }

    @CommandHandler(10)
    public void deleteAuthorByID() {
        commands.stream().filter(command -> command instanceof DeleteAuthorByIdOperation)
                .findFirst().get()
                .execute();
    }


}