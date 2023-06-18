package com.mjc.school.controller.operation.operationImpl;

import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.utils.Operations;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.dto.NewsParamsDto;
import com.mjc.school.service.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mjc.school.controller.utils.Constants.*;

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
        System.out.println(OPERATION + Operations.GET_NEWS_BY_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(NEWS_ID);
            System.out.println(newsController.readById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createNews() {
        System.out.println(OPERATION + Operations.CREATE_NEWS.getOperationDescription());
        System.out.println(ENTER_NEWS_TITLE);
        NewsDto newsRequestDto = new NewsDto();
        try {
            String title = scanner.nextLine();
            newsRequestDto.setTitle(title);

            System.out.println(ENTER_NEWS_CONTENT);
            String content = scanner.nextLine();
            newsRequestDto.setContent(content);

            System.out.println(ENTER_AUTHOR_ID);
            long authorId = validateNumberInput(AUTHOR_ID);
            newsRequestDto.setAuthorId(authorId);

            System.out.println(ENTER_TAGS_ID);
            Set<Long> tagsId = validateNumberArrayInput(TAGS_ID);
            Set<TagDto> tags = tagsId.stream().map(id -> {
                TagDto tagDto = new TagDto();
                tagDto.setId(id);
                return tagDto;
            }).collect(Collectors.toSet());
            newsRequestDto.setTags(tags);

            System.out.println(newsController.create(newsRequestDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateNews() {
        System.out.println(OPERATION + Operations.UPDATE_NEWS.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        NewsDto newsDto = new NewsDto();
        try {
            newsDto.setId(validateNumberInput(NEWS_ID));
            System.out.println(ENTER_NEWS_TITLE);
            newsDto.setTitle(scanner.nextLine());

            System.out.println(ENTER_NEWS_CONTENT);
            newsDto.setContent(scanner.nextLine());

            System.out.println(ENTER_AUTHOR_ID);
            newsDto.setAuthorId(validateNumberInput(AUTHOR_ID));

            System.out.println(ENTER_TAGS_ID);
            Set<Long> tagsId = validateNumberArrayInput(TAGS_ID);
            Set<TagDto> tags = tagsId.stream().map(id -> {
                TagDto tagDto = new TagDto();
                tagDto.setId(id);
                return tagDto;
            }).collect(Collectors.toSet());
            newsDto.setTags(tags);

            System.out.println(newsController.update(newsDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteNewById() {
        System.out.println(OPERATION + Operations.REMOVE_NEWS_BY_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(NEWS_ID);
            System.out.println(newsController.deleteById(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void getAuthorByNewsId() {
        System.out.println(OPERATION + Operations.GET_AUTHOR_BY_NEWS_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(NEWS_ID);
            System.out.println(newsController.getAuthorByNewsId(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void getTagByNewsId() {
        System.out.println(OPERATION + Operations.GET_TAGS_BY_NEWS_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(NEWS_ID);
            System.out.println(newsController.getTagByNewsId(id));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void getNewsByParams() {
        System.out.println(OPERATION + Operations.GET_NEWS_BY_PARAMS.getOperationDescription());
        String authorName;
        String newsTitle;
        String newsContent;
        List<String> tagsName;
        Set<Long> tagsId;
        NewsParamsDto newsParamsDtoRequest = new NewsParamsDto();

        System.out.println(ENTER_AUTHOR_NAME);
        newsParamsDtoRequest.setAuthorName(scanner.nextLine());

        System.out.println(ENTER_NEWS_TITLE);
        newsParamsDtoRequest.setTitle(scanner.nextLine());

        System.out.println(ENTER_NEWS_CONTENT);
        newsParamsDtoRequest.setContent(scanner.nextLine());


        System.out.println(ENTER_TAGS_NAME);
        newsParamsDtoRequest.setTagNames(List.of(scanner.nextLine().split(" ")));

        System.out.println(ENTER_TAGS_ID);
        newsParamsDtoRequest.setTagIds(validateNumberArrayInput(TAG_ID));

        List<NewsDto> newsByParams = newsController.getNewsByParams(newsParamsDtoRequest);
        System.out.println(newsByParams);
    }
}