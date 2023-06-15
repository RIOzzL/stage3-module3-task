package com.mjc.school.service.aop.validator;

import com.mjc.school.repository.model.entity.Tag;
import com.mjc.school.service.aop.validator.restriction.Size;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.impl.TagService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static com.mjc.school.service.exception.ServiceError.*;
import static com.mjc.school.service.exception.ServiceError.AUTHOR_ID_DOES_NOT_EXIST;

@Component
public class TagValidator implements Validator<TagDto> {

    private final TagService tagService;

    @Autowired
    public TagValidator(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public boolean isValid(TagDto tag) {
        return false;
    }

    @Override
    public boolean updateValidation(TagDto tag) {
        isExistValidation(tag.getId());
        StringBuilder errorMessage = new StringBuilder();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(tag.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            errorMessage.append(sizeAnnotationValidation(tag));
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidatorException(errorMessage.toString());
        }
        return true;
    }

    @Override
    public boolean createValidation(TagDto tag) {
        StringBuilder errorMessage = new StringBuilder();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(tag.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            errorMessage.append(sizeAnnotationValidation(tag));
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidatorException(errorMessage.toString());
        }
        return true;
    }

    @SneakyThrows
    @Override
    public String sizeAnnotationValidation(TagDto tag) {
        Field[] declaredFieldsOfNewsDto = tag.getClass().getDeclaredFields();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(declaredFieldsOfNewsDto).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        StringBuilder errorMessage = new StringBuilder();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            for (Field field : fieldsWithSizeAnnotation) {
                Size annotation = field.getAnnotation(Size.class);
                int minSize = annotation.min();
                int maxSize = annotation.max();
                field.setAccessible(true);
                String fieldSizeAnnotatedValue = (String) field.get(tag);
                if (fieldSizeAnnotatedValue.length() < minSize || fieldSizeAnnotatedValue.length() > maxSize) {
                    errorMessage.append(String.format(VALIDATE_STRING_LENGTH.getMessage(), field.getName(),
                            minSize, maxSize, field.getName())).append("\n");
                }
            }
            return errorMessage.toString();
        }
        return "";
    }

    @Override
    public boolean isExistValidation(Long id) {
        boolean entityExist = tagService.isTagExist(id);
        if (entityExist) {
            return true;
        } else {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}
