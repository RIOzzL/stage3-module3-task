package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.aop.validator.restriction.CreateValid;
import com.mjc.school.service.aop.validator.restriction.IsEntityExist;
import com.mjc.school.service.aop.validator.restriction.UpdateValid;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.impl.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorDto, AuthorDto, Long> {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<AuthorDto> readAll() {
        return authorService.readAll();
    }

    @Override
    public AuthorDto readById(Long id) {
        return authorService.readById(id);
    }

    @Override
    public AuthorDto create(AuthorDto createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    public AuthorDto update(AuthorDto updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}
