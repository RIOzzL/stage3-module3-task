package com.mjc.school.controller.utils;

import lombok.Getter;

@Getter
public enum Operations {
    GET_ALL_NEWS(1, "Get all news."),
    GET_ALL_AUTHORS(2, "Get all authors."),
    GET_NEWS_BY_ID(3, "Get news by id."),
    GET_AUTHOR_BY_ID(4, "Get author by id."),
    CREATE_NEWS(5, "Create news."),
    CREATE_AUTHOR(6, "Create author."),
    UPDATE_NEWS(7, "Update news."),
    UPDATE_AUTHOR(8, "Update authors."),
    REMOVE_NEWS_BY_ID(9, "Remove news by id."),
    REMOVE_AUTHOR_BY_ID(10, "Remove author by id."),
    EXIT(11, "Exit");

    private int operationNumber;
    private String operationDescription;

    Operations(int operationNumber, String operationDescription) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
    }
}
