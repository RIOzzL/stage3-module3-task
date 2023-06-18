package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.dto.NewsParamsDto;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.impl.NewsService;
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
    public NewsDto readById(Long id) {
        NewsDto newsDto = newsService.readById(id);
        return newsDto;
    }

    @Override
    public NewsDto create(NewsDto createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    public NewsDto update(NewsDto updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }

    public AuthorDto getAuthorByNewsId(long id) {
        return newsService.getAuthorByNewsId(id);
    }

    public List<TagDto> getTagByNewsId(long id) {
        return newsService.getTagByNewsId(id);
    }

    public List<NewsDto> getNewsByParams(NewsParamsDto newsParamsDtoRequest) {
        return newsService.getNewsByParams(newsParamsDtoRequest);
    }
}
