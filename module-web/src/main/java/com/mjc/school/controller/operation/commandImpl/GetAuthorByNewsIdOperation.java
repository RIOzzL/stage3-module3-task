package com.mjc.school.controller.operation.commandImpl;

import com.mjc.school.controller.operation.Command;
import com.mjc.school.controller.operation.operationImpl.NewsOperationsExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAuthorByNewsIdOperation implements Command {

    private final NewsOperationsExecutor operation;

    @Autowired
    public GetAuthorByNewsIdOperation(NewsOperationsExecutor operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        operation.getAuthorByNewsId();
    }
}
