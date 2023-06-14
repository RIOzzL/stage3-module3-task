package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.impl.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TagController implements BaseController<TagDto, TagDto, Long> {

    private final TagService tagService;

    @Override
    public List<TagDto> readAll() {
        return tagService.readAll();
    }

    @Override
    public TagDto readById(Long id) {
        return tagService.readById(id);
    }

    @Override
    public TagDto create(TagDto createRequest) {
        return tagService.create(createRequest);
    }

    @Override
    public TagDto update(TagDto updateRequest) {
        return tagService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return tagService.deleteById(id);
    }
}
