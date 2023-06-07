package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.entity.News;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mjc.school.service.exception.ServiceError.NEWS_ID_DOES_NOT_EXIST;

@Service
public class NewsService implements BaseService<NewsDto, NewsDto, Long> {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }


    @Override
    public List<NewsDto> readAll() {
        List<News> allNews = newsRepository.readAll();
        return allNews.stream().map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto readById(Long id) {
        Optional<News> news = newsRepository.readById(id);
        if (news.isPresent()) {
            return newsMapper.toDto(news.get());
        } else {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    public NewsDto create(NewsDto createRequest) {
        News news = newsRepository.create(newsMapper.toEntity(createRequest));
        return newsMapper.toDto(news);
    }

    @Override
    public NewsDto update(NewsDto updateRequest) {
        News update = newsRepository.update(newsMapper.toEntity(updateRequest));
        return newsMapper.toDto(update);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }


    public boolean isEntityExist(Long id) {
        Optional<News> news = newsRepository.readById(id);
        return news.isPresent();
    }
}
