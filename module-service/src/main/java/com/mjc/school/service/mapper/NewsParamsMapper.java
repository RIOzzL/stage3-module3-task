package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.params.NewsParams;
import com.mjc.school.service.dto.NewsParamsDto;
import org.springframework.stereotype.Component;

@Component
public class NewsParamsMapper implements BaseMapper<NewsParamsDto, NewsParams> {


    @Override
    public NewsParams toEntity(NewsParamsDto newsParamsDto) {
        return mapper.map(newsParamsDto, NewsParams.class);
    }

    @Override
    public NewsParamsDto toDto(NewsParams newsParams) {
        return mapper.map(newsParams, NewsParamsDto.class);
    }
}
