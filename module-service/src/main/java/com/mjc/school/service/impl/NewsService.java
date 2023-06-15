package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.repository.model.entity.News;
import com.mjc.school.repository.model.entity.Tag;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aop.validator.restriction.CreateValid;
import com.mjc.school.service.aop.validator.restriction.IsEntityExist;
import com.mjc.school.service.aop.validator.restriction.UpdateValid;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService implements BaseService<NewsDto, NewsDto, Long> {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;
    private final AuthorMapper authorMapper;
    private final TagMapper tagMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, AuthorMapper authorMapper, TagMapper tagMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
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
//        if (news.isPresent()) {
        return newsMapper.toDto(news.get());
//        } else {
//            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
//        }
    }

    @Override
    @Transactional
    @CreateValid
    public NewsDto create(NewsDto createRequest) {
        News news = newsRepository.create(newsMapper.toEntity(createRequest));
        return newsMapper.toDto(news);
    }

    @Override
    @Transactional
    @UpdateValid
    public NewsDto update(NewsDto updateRequest) {
        News update = newsRepository.update(newsMapper.toEntity(updateRequest));
        return newsMapper.toDto(update);
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
}
