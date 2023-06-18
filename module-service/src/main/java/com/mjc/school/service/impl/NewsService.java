package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.repository.model.entity.News;
import com.mjc.school.repository.model.entity.Tag;
import com.mjc.school.repository.model.params.NewsParams;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aop.validator.restriction.CreateValid;
import com.mjc.school.service.aop.validator.restriction.IsEntityExist;
import com.mjc.school.service.aop.validator.restriction.UpdateValid;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.dto.NewsParamsDto;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsParamsMapper;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements BaseService<NewsDto, NewsDto, Long> {

    private final NewsRepository newsRepository;
    private final TagRepository tagRepository;
    private final AuthorRepository authorRepository;

    private final NewsMapper newsMapper;
    private final NewsParamsMapper newsParamsMapper;
    private final AuthorMapper authorMapper;
    private final TagMapper tagMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, TagRepository tagRepository, AuthorRepository authorRepository, NewsMapper newsMapper, NewsParamsMapper newsParamsMapper, AuthorMapper authorMapper, TagMapper tagMapper) {
        this.newsRepository = newsRepository;
        this.tagRepository = tagRepository;
        this.authorRepository = authorRepository;
        this.newsMapper = newsMapper;
        this.newsParamsMapper = newsParamsMapper;
        this.authorMapper = authorMapper;
        this.tagMapper = tagMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<NewsDto> readAll() {
        List<News> allNews = newsRepository.readAll();
        return allNews.stream().map(newsMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NewsDto readById(@IsEntityExist Long id) {
        Optional<News> news = newsRepository.readById(id);
        return newsMapper.toDto(news.get());
    }

    @Override
    @Transactional
    @CreateValid
    public NewsDto create(NewsDto createRequest) {
        // Validation of all news dto is performed in AOP validator
        News newsToCreate = prepareNewsDtoToSavaOrUpdate(createRequest);
        News news = newsRepository.create(newsToCreate);
        return newsMapper.toDto(news);
    }

    @Override
    @Transactional
    @UpdateValid
    public NewsDto update(NewsDto updateRequest) {
        // Validation of all news dto is performed in AOP validator
        News newsToUpdate = prepareNewsDtoToSavaOrUpdate(updateRequest);
        //   newsToUpdate.setAuthor(authorRepository.readById(updateRequest.getAuthorId());
        News update = newsRepository.update(newsToUpdate);
        return newsMapper.toDto(update);
    }

    private News prepareNewsDtoToSavaOrUpdate(NewsDto newsDto) {
        List<Long> tagsId = newsDto.getTags().stream().map(TagDto::getId).toList();
        News newsToUpdateOrSave = newsMapper.toEntity(newsDto);
        newsToUpdateOrSave.getTags().clear();

        for (Long id : tagsId) {
            // Also if we pass Nonexistent tag id, we will check it in NewsDtoValidator
            newsToUpdateOrSave.getTags().add(tagRepository.readById(id).get());
        }
        return newsToUpdateOrSave;
    }

    @Override
    @Transactional
    public boolean deleteById(@IsEntityExist Long id) {
        return newsRepository.deleteById(id);
    }


    @Transactional
    public boolean isEntityExist(Long id) {
        return newsRepository.existById(id);
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthorByNewsId(@IsEntityExist long id) {
        Author author = newsRepository.getAuthorByNewsId(id);
        return authorMapper.toDto(author);
    }

    @Transactional(readOnly = true)
    public List<TagDto> getTagByNewsId(@IsEntityExist long id) {
        List<Tag> tags = newsRepository.getTagByNewsId(id);
        return tags.stream().map(tagMapper::toDto).toList();
    }

    @Transactional
    public List<NewsDto> getNewsByParams(NewsParamsDto newsParamsDtoRequest) {
        // Here we map to model, not to Entity
        NewsParams newsParams = newsParamsMapper.toEntity(newsParamsDtoRequest);
        List<News> newsByParams = newsRepository.getNewsByParams(newsParams);
        List<NewsDto> newsDtos = newsByParams.stream().map(newsMapper::toDto).toList();
        return newsDtos;
    }
}
