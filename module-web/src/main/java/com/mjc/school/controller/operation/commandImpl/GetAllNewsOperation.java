package com.mjc.school.controller.operation.commandImpl;

import com.mjc.school.controller.operation.Command;
import com.mjc.school.controller.operation.NewsOperationsExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllNewsOperation implements Command {

    private NewsOperationsExecutor operation;

    @Autowired
    public GetAllNewsOperation(NewsOperationsExecutor operation) {
        this.operation = operation;
    }


    @Override
    public void execute() {
        operation.printAllNews();
    }
}
