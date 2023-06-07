package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.entity.News;
import com.mjc.school.repository.util.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<News, Long> {
    private final DataSource dataSource;

    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<News> readAll() {
        return dataSource.getNewsList();
    }

    @Override
    public Optional<News> readById(Long id) {
        return dataSource.getNewsList().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();
    }

    @Override
    public News create(News entity) {
        entity.setId(dataSource.increaseNewsId());
        dataSource.getNewsList().add(entity);
        return entity;
    }

    @Override
    public News update(News entity) {
        News newsToUpdate = this.readById(entity.getId()).get();
        newsToUpdate.setTitle(entity.getTitle());
        newsToUpdate.setContent(entity.getContent());
        newsToUpdate.setAuthorId(entity.getAuthorId());
        newsToUpdate.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return newsToUpdate;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<News> newsToDelete = dataSource.getNewsList().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();
        if (newsToDelete.isPresent()) {
            dataSource.getNewsList().remove(newsToDelete.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        return this.readById(id).isPresent();
    }
}
