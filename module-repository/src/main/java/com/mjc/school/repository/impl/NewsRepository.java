package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.entity.News;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<News, Long> {

    private static final String READ_ALL = "SELECT n FROM News n";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> readAll() {
        return entityManager.createQuery(READ_ALL, News.class).getResultList();
    }

    @Override
    public Optional<News> readById(Long id) {
        return Optional.ofNullable(entityManager.find(News.class, id));
    }

    @Override
    public News create(News entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public News update(News entity) {
        if (existById(entity.getId())) {
            News newsToUpdate = this.readById(entity.getId()).get();
            newsToUpdate.setTitle(entity.getTitle());
            newsToUpdate.setContent(entity.getContent());
            newsToUpdate.setAuthor(entity.getAuthor());
            newsToUpdate.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            entityManager.persist(newsToUpdate);
            return newsToUpdate;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            News news = readById(id).get();
            entityManager.remove(news);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return this.readById(id).isPresent();
    }
}
