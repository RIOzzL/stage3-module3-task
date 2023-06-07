package com.mjc.school.service.validator;

import lombok.SneakyThrows;

public interface Validator<T> {

    public boolean isValid(T t);


    boolean updateValidation(T t);

    @SneakyThrows
    boolean createValidation(T t);

    String sizeAnnotationValidation(T t) throws IllegalAccessException;

    boolean isExistValidation(Long id);
}
