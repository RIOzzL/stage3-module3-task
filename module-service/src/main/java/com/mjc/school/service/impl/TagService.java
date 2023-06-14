package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.entity.Tag;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mjc.school.service.exception.ServiceError.TAG_ID_DOES_NOT_EXIST;

@Service
public class TagService implements BaseService<TagDto, TagDto, Long> {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Autowired
    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDto> readAll() {
        List<Tag> tags = tagRepository.readAll();
        return tags.stream().map(tagMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto readById(Long id) {
        Optional<Tag> tag = tagRepository.readById(id);
        if (tag.isPresent()) {
            return tagMapper.toDto(tag.get());
        } else {
            throw new ValidatorException(String.format(TAG_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    public TagDto create(TagDto createRequest) {
        Tag tag = tagRepository.create(tagMapper.toEntity(createRequest));
        return tagMapper.toDto(tag);
    }

    @Override
    public TagDto update(TagDto updateRequest) {
        Tag tag = tagRepository.update(tagMapper.toEntity(updateRequest));
        return tagMapper.toDto(tag);
    }

    @Override
    public boolean deleteById(Long id) {
        return tagRepository.deleteById(id);
    }
}
