package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.service.dto.AuthorDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorMapper implements BaseMapper<AuthorDto, Author> {

    @Override
    public Author toEntity(AuthorDto authorDto) {
        return Objects.isNull(authorDto) ? null : mapper.map(authorDto, Author.class);
    }

    @Override
    public AuthorDto toDto(Author author) {
        return Objects.isNull(author) ? null : mapper.map(author, AuthorDto.class);
    }
}
