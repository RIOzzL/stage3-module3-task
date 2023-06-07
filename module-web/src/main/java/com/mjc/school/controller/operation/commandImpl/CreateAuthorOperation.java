package com.mjc.school.controller.operation.commandImpl;

import com.mjc.school.controller.operation.AuthorOperationExecutor;
import com.mjc.school.controller.operation.Command;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorOperation implements Command {

    private final AuthorOperationExecutor operation;

    public CreateAuthorOperation(AuthorOperationExecutor operation) {
        this.operation = operation;
    }


    @Override
    public void execute() {
        operation.createAuthor();
    }
}
