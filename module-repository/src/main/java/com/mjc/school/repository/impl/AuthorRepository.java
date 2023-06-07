package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.aop.annotation.OnDelete;
import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.repository.util.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<Author, Long> {

    private final DataSource dataSource;

    @Autowired
    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Author> readAll() {
        return dataSource.getAuthorList();
    }

    @Override
    public Optional<Author> readById(Long id) {
        return dataSource.getAuthorList().stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public Author create(Author entity) {
        entity.setId(dataSource.increaseAuthorId());
        entity.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        entity.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        dataSource.getAuthorList().add(entity);
        return entity;
    }

    @Override
    public Author update(Author entity) {
        Author author = this.readById(entity.getId()).get();
        author.setName(entity.getName());
        author.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return author;
    }


    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        Optional<Author> authorToDelete = dataSource.getAuthorList().stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
        if (authorToDelete.isPresent()) {
            dataSource.getAuthorList().remove(authorToDelete.get());
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