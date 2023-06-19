package com.mjc.school.controller.operation.operationImpl;

import com.mjc.school.controller.impl.TagController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Operations;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.exception.InputValidatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mjc.school.controller.utils.Operations.REMOVE_TAG_BY_ID;
import static com.mjc.school.controller.utils.Operations.UPDATE_TAG;

@Component
@RequiredArgsConstructor
public class TagOperationExecutor extends OperationExecutor {

    private final TagController tagController;

    public void printAllTags() {
        System.out.println(tagController.readAll());
    }

    public void getTagById() {
        System.out.println(Constants.OPERATION + Operations.GET_TAG_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_TAG_ID);
        try {
            long id = validateNumberInput(Constants.TAG_ID);
            System.out.println(tagController.readById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createTag() {
        System.out.println(Constants.OPERATION + Operations.CREATE_TAG.getOperationDescription());
        System.out.println(Constants.ENTER_TAG_NAME);
        TagDto tagDto = new TagDto();
        try {
            String name = scanner.nextLine();
            tagDto.setName(name);
            System.out.println(tagController.create(tagDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateTag() {
        System.out.println(Constants.OPERATION + UPDATE_TAG.getOperationDescription());
        System.out.println(Constants.ENTER_TAG_NAME);
        TagDto tagDto = new TagDto();
        try {
            scanner.nextLine();
            String tagName = scanner.nextLine();
            tagDto.setName(tagName);
            System.out.println(tagController.update(tagDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteTagById() {
        System.out.println(Constants.OPERATION + REMOVE_TAG_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_TAG_ID);
        try {
            long id = validateNumberInput(Constants.TAG_ID);
            System.out.println(tagController.deleteById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
