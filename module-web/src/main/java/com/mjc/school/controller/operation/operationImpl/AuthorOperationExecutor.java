package com.mjc.school.controller.operation.operationImpl;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.service.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mjc.school.controller.utils.Operations.*;

@Component
public class AuthorOperationExecutor extends OperationExecutor {

    private final AuthorController authorController;

    @Autowired
    public AuthorOperationExecutor(AuthorController authorController) {
        this.authorController = authorController;
    }

    public void printAllAuthors() {
        System.out.println(authorController.readAll());
    }

    public void getAuthorById() {
        System.out.println(Constants.OPERATION + GET_AUTHOR_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_AUTHOR_ID);
        try {
            Long id = validateNumberInput(Constants.AUTHOR_ID);
            System.out.println(authorController.readById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createAuthor() {
        System.out.println(Constants.OPERATION + CREATE_AUTHOR.getOperationDescription());
        System.out.println(Constants.ENTER_AUTHOR_NAME);
        AuthorDto authorDto = new AuthorDto();
        try {
            authorDto.setName(scanner.next());
            System.out.println(authorController.create(authorDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateAuthor() {
        System.out.println(Constants.OPERATION + UPDATE_AUTHOR.getOperationDescription());
        System.out.println(Constants.ENTER_AUTHOR_NAME);
        AuthorDto authorRequestDto = new AuthorDto();
        try {
            scanner.nextLine();
            String authorName = scanner.nextLine();
            authorRequestDto.setName(authorName);
            System.out.println(Constants.ENTER_AUTHOR_ID);
            authorRequestDto.setId(validateNumberInput(Constants.AUTHOR_ID));
            System.out.println(authorController.update(authorRequestDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteAuthorById() {
        System.out.println(Constants.OPERATION + REMOVE_AUTHOR_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_AUTHOR_ID);
        try {
            long id = validateNumberInput(Constants.AUTHOR_ID);
            System.out.println(authorController.deleteById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
