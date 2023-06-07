package com.mjc.school.service.mapper;

public interface BaseMapper<T, K> {

    K toEntity(T t);

    T toDto(K k);
}
