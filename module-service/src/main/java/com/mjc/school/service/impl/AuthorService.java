package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aop.validator.restriction.CreateValid;
import com.mjc.school.service.aop.validator.restriction.IsEntityExist;
import com.mjc.school.service.aop.validator.restriction.UpdateValid;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mjc.school.service.exception.ServiceError.AUTHOR_ID_DOES_NOT_EXIST;

@Service
public class AuthorService implements BaseService<AuthorDto, AuthorDto, Long> {

    private final AuthorRepository authorRepository;

    private final AuthorMapper mapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> readAll() {
        List<Author> authors = authorRepository.readAll();
        return authors.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto readById(@IsEntityExist Long id) {
        Optional<Author> author = authorRepository.readById(id);
        if (author.isPresent()) {
            return mapper.toDto(author.get());
        } else {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    @CreateValid
    public AuthorDto create(AuthorDto createRequest) {
        Author author = mapper.toEntity(createRequest);
        return mapper.toDto(authorRepository.create(author));
    }

    @Override
    @Transactional
    @UpdateValid
    public AuthorDto update(AuthorDto updateRequest) {
        Author updatedAuthor = mapper.toEntity(updateRequest);
        Author authorToBeUpdate = authorRepository.readById(updatedAuthor.getId()).get();
        authorToBeUpdate.setName(updatedAuthor.getName());
        authorToBeUpdate.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return mapper.toDto(authorToBeUpdate);
    }

    @Override
    @Transactional
    public boolean deleteById(@IsEntityExist Long id) {
        return authorRepository.deleteById(id);
    }

    @Transactional
    public boolean existById(Long id) {
        return authorRepository.existById(id);
    }
}
