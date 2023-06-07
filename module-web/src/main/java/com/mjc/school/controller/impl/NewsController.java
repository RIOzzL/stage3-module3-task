package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.validator.restriction.CreateValid;
import com.mjc.school.service.validator.restriction.IsEntityExist;
import com.mjc.school.service.validator.restriction.UpdateValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDto, NewsDto, Long> {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @Override
    public List<NewsDto> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDto readById(@IsEntityExist Long id) {
        NewsDto newsDto = newsService.readById(id);
        return newsDto;
    }

    @Override
    @CreateValid
    public NewsDto create(NewsDto createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @UpdateValid
    public NewsDto update(NewsDto updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    public boolean deleteById(@IsEntityExist Long id) {
        return newsService.deleteById(id);
    }
}
