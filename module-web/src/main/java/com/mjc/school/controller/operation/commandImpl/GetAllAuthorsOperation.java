package com.mjc.school.controller.operation.commandImpl;

import com.mjc.school.controller.operation.AuthorOperationExecutor;
import com.mjc.school.controller.operation.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllAuthorsOperation implements Command {

    private AuthorOperationExecutor operation;

    @Autowired
    public GetAllAuthorsOperation(AuthorOperationExecutor operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        System.out.println("Execute from - GetAllAuthorsOperation!!!");
        operation.printAllAuthors();
    }
}
