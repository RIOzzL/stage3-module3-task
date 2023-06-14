package com.mjc.school.service.mapper;

import org.modelmapper.ModelMapper;

public interface BaseMapper<T, K> {

    ModelMapper mapper = new ModelMapper();

    K toEntity(T t);

    T toDto(K k);
}
