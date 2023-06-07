package com.mjc.school.controller.operation.commandImpl;

import com.mjc.school.controller.operation.AuthorOperationExecutor;
import com.mjc.school.controller.operation.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAuthorByIdOperation implements Command {

    private final AuthorOperationExecutor operation;

    @Autowired
    public GetAuthorByIdOperation(AuthorOperationExecutor operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        operation.getAuthorById();
    }
}
