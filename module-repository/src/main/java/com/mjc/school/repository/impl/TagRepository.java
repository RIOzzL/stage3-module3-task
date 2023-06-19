package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<Tag, Long> {

    private static final String READ_ALL = "SELECT t FROM Tag t";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> readAll() {
        return entityManager.createQuery(READ_ALL, Tag.class).getResultList();
    }

    @Override
    public Optional<Tag> readById(Long id) {
        Tag tag = entityManager.find(Tag.class, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public Tag create(Tag entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Tag update(Tag entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            Tag tag = readById(id).get();
            entityManager.remove(tag);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
