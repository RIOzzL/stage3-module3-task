package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.aop.annotation.OnDelete;
import com.mjc.school.repository.model.entity.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<Author, Long> {

    private static final String READ_ALL = "SELECT a FROM Author a";
    private static final String READ_BY_ID = "SELECT a FROM Author a WHERE a.id = :id";
    private static final String UPDATE_AUTHOR = "UPDATE Author a set  a.name = :name, a.createDate = :create_date," +
            "a.lastUpdateDate = :last_update_date, a.news = :news " +
            "where a.id = :id";
    private static final String DELETE_BY_ID = "DELETE FROM Author a WHERE a.id = :id";
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Author> readAll() {
        return entityManager.createQuery(READ_ALL, Author.class).getResultList();
    }

    @Override
    public Optional<Author> readById(Long id) {
        return Optional.ofNullable(entityManager.createQuery(READ_BY_ID, Author.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public Author create(Author entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Author update(Author updatedAuthor) {
        // Do not need existById
        if (existById(updatedAuthor.getId())) {
            int updatedRow = entityManager.createQuery(UPDATE_AUTHOR, Author.class)
                    .setParameter("name", updatedAuthor.getName())
                    .setParameter("create_date", updatedAuthor.getCreateDate())
                    .setParameter("last_update_date", updatedAuthor.getLastUpdateDate())
                    .setParameter("news", updatedAuthor.getNews())
                    .setParameter("id", updatedAuthor.getId())
                    .executeUpdate();
            return readById(updatedAuthor.getId()).get();
        } else {
            return null;
        }
    }


    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        int deletedRow = entityManager.createQuery(DELETE_BY_ID)
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean existById(Long id) {
        return this.readById(id).isPresent();
    }
}