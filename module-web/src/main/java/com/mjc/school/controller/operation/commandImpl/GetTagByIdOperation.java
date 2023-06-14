package com.mjc.school.controller.operation.commandImpl;

import com.mjc.school.controller.operation.Command;
import com.mjc.school.controller.operation.operationImpl.TagOperationExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTagByIdOperation implements Command {


    private final TagOperationExecutor tagOperationExecutor;

    @Override
    public void execute() {
        tagOperationExecutor.getTagById();
    }
}
