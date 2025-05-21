package com.app.core.service;

import com.app.shared.Identifiable;

import java.util.List;

public interface BaseService<T extends Identifiable, D, R> {
    T create(T entity);
    List<R> findAllDtos();
    R findDtoById(Long id);
    T update(Long id, T entity);
    void delete(Long id);
}
