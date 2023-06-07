package com.mjc.school.controller.operation;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.service.exception.InputErrorMessage;
import com.mjc.school.service.exception.InputValidatorException;
import com.mjc.school.controller.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.utils.Operations.*;

@Component
public class AuthorOperationExecutor {

    private final AuthorController authorController;
    private final Scanner scanner;

    @Autowired
    public AuthorOperationExecutor(AuthorController authorController) {
        this.authorController = authorController;
        this.scanner = new Scanner(System.in);
    }

    public void printAllAuthors() {
        System.out.println(authorController.readAll());
    }

    public void getAuthorById() {
        System.out.println(Constants.OPERATION + GET_AUTHOR_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_AUTHOR_ID);
        try {
            Long id = validateNumberInput(scanner, Constants.AUTHOR_ID);
            System.out.println(authorController.readById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createAuthor() {
        System.out.println(Constants.OPERATION + CREATE_AUTHOR.getOperationDescription());
        System.out.println(Constants.ENTER_AUTHOR_NAME);
      //  AuthorDto authorDto = new AuthorDto();
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
     //   AuthorDto authorDto = new AuthorDto();
        AuthorDto authorRequestDto = new AuthorDto();
        try {
            scanner.nextLine();
            String authorName = scanner.nextLine();
            authorRequestDto.setName(authorName);
            System.out.println(Constants.ENTER_AUTHOR_ID);
            authorRequestDto.setId(validateNumberInput(scanner, Constants.AUTHOR_ID));
            System.out.println(authorController.update(authorRequestDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteAuthorById() {
        System.out.println(Constants.OPERATION + REMOVE_AUTHOR_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_AUTHOR_ID);
        try {
            long id = validateNumberInput(scanner, Constants.AUTHOR_ID);
            System.out.println(authorController.deleteById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Long validateNumberInput(Scanner scanner, String param) {
        long nextLong;
        try {
            nextLong = scanner.nextLong();
        } catch (RuntimeException exception) {
            throw new InputValidatorException(String.format(InputErrorMessage.VALIDATE_INT_VALUE.getMessage(), param));
        }
        return nextLong;
    }
}
