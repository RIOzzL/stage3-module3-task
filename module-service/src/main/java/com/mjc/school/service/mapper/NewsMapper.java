package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.entity.News;
import com.mjc.school.service.dto.NewsDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NewsMapper implements BaseMapper<NewsDto, News> {

    @Override
    public News toEntity(NewsDto newsDto) {
        return Objects.isNull(newsDto) ? null : mapper.map(newsDto, News.class);
    }

    @Override
    public NewsDto toDto(News news) {
        return Objects.isNull(news) ? null : mapper.map(news, NewsDto.class);
    }
}
