package com.app.core.mapper;

public interface BaseMapper<T, D, R> {
    T toEntity(D dto);
    R toDto(T entity);
}
