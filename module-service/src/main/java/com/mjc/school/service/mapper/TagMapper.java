package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.entity.Tag;
import com.mjc.school.service.dto.TagDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagMapper implements BaseMapper<TagDto, Tag> {
    @Override
    public Tag toEntity(TagDto tagDto) {
        return Objects.isNull(tagDto) ? null : mapper.map(tagDto, Tag.class);
    }

    @Override
    public TagDto toDto(Tag tag) {
        return Objects.isNull(tag) ? null : mapper.map(tag, TagDto.class);
    }
}
