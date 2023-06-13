package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.aop.annotation.OnDelete;
import com.mjc.school.repository.model.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<Author, Long> {

    private static final String READ_ALL = "SELECT a FROM Author a";

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Author> readAll() {
        return entityManager.createQuery(READ_ALL, Author.class).getResultList();
    }

    @Override
    public Optional<Author> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Author create(Author entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Author update(Author updatedAuthor) {
        if (existById(updatedAuthor.getId())) {
            Author author = this.readById(updatedAuthor.getId()).get();
            author.setName(updatedAuthor.getName());
            entityManager.merge(author);
            return author;
        } else {
            return null;
        }
    }


    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            Author author = readById(id).get();
            entityManager.remove(author);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return this.readById(id).isPresent();
    }
}