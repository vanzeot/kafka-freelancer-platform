package com.app.core.service;

import com.app.core.mapper.BaseMapper;
import com.app.shared.Identifiable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CrudService<T extends Identifiable, D, R> implements BaseService<T, D, R> {

    protected final JpaRepository<T, Long> repository;
    protected final BaseMapper<T, D, R> mapper;

    protected CrudService(JpaRepository<T, Long> repository, BaseMapper<T, D, R> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<R> findAllDtos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public R findDtoById(Long id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        return mapper.toDto(entity);
    }

    @Override
    public T update(Long id, T entity) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
