package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.aop.annotation.OnDelete;
import com.mjc.school.repository.model.entity.Author;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<Author, Long> {

    private static final String READ_ALL = "SELECT a FROM Author a";
    private static final String READ_BY_ID = "SELECT a FROM Author a WHERE a.id = :id";
    private static final String UPDATE_AUTHOR = "UPDATE Author a set  a.name = :name, a.createDate = :create_date," +
            "a.lastUpdateDate = :last_update_date, a.news = :news " +
            "where a.id = :id";
    private static final String DELETE_BY_ID = "DELETE Author a WHERE a.id = :id";

    // Repository with using Hibernate implementation JPA
    @PersistenceContext
    private Session session;


    @Override
    public List<Author> readAll() {
        return session.createQuery(READ_ALL, Author.class).list();
    }

    @Override
    public Optional<Author> readById(Long id) {
        return session.createQuery(READ_BY_ID, Author.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public Author create(Author entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public Author update(Author updatedAuthor) {
        if (existById(updatedAuthor.getId())) {
            int updatedRow = session.createQuery(UPDATE_AUTHOR, Author.class)
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
        if (existById(id)) {
            int deletedRow = session.createQuery(DELETE_BY_ID, Author.class)
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return this.readById(id).isPresent();
    }
}