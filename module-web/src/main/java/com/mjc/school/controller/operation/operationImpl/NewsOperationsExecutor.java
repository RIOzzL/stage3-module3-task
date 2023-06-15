package com.mjc.school.controller.operation.operationImpl;

import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Operations;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.InputValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsOperationsExecutor extends OperationExecutor {

    private final NewsController newsController;

    @Autowired
    public NewsOperationsExecutor(NewsController newsController) {
        this.newsController = newsController;
    }

    public void printAllNews() {
        System.out.println(newsController.readAll());
    }

    public void getNewsById() {
        System.out.println(Constants.OPERATION + Operations.GET_NEWS_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(Constants.NEWS_ID);
            System.out.println(newsController.readById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createNews() {
        System.out.println(Constants.OPERATION + Operations.CREATE_NEWS.getOperationDescription());
        System.out.println(Constants.ENTER_NEWS_TITLE);
        NewsDto newsRequestDto = new NewsDto();
        try {
            String title = scanner.nextLine();
            newsRequestDto.setTitle(title);
            System.out.println(Constants.ENTER_NEWS_CONTENT);
            String content = scanner.nextLine();
            newsRequestDto.setContent(content);
            System.out.println(Constants.ENTER_AUTHOR_ID);
            long authorId = validateNumberInput(Constants.AUTHOR_ID);
            newsRequestDto.setAuthorId(authorId);
            System.out.println(newsController.create(newsRequestDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateNews() {
        System.out.println(Constants.OPERATION + Operations.UPDATE_NEWS.getOperationDescription());
        System.out.println(Constants.ENTER_NEWS_ID);
        NewsDto newsDto = new NewsDto();
        try {
            newsDto.setId(validateNumberInput(Constants.NEWS_ID));
            System.out.println(Constants.ENTER_NEWS_TITLE);
            scanner.nextLine();
            newsDto.setTitle(scanner.nextLine());
            System.out.println(Constants.ENTER_NEWS_CONTENT);
            newsDto.setContent(scanner.nextLine());
            System.out.println(Constants.ENTER_AUTHOR_ID);
            newsDto.setAuthorId(validateNumberInput(Constants.AUTHOR_ID));
            System.out.println(newsController.update(newsDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteNewById() {
        System.out.println(Constants.OPERATION + Operations.REMOVE_NEWS_BY_ID.getOperationDescription());
        System.out.println(Constants.ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(Constants.NEWS_ID);
            System.out.println(newsController.deleteById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void getAuthorByNewsId() {
        System.out.println(Constants.OPERATION + Operations.GET_AUTHOR_BY_NEWS_ID.getOperationDescription());
        System.out.println(Constants.ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(Constants.NEWS_ID);
            System.out.println(newsController.getAuthorByNewsId(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}