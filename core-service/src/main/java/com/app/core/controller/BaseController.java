package com.app.core.controller;

import com.app.core.mapper.BaseMapper;
import com.app.shared.Identifiable;
import com.app.core.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T extends Identifiable, D, R> {

    protected final BaseService<T, D, R> service;
    protected final BaseMapper<T, D, R> mapper;

    protected BaseController(BaseService<T, D, R> service, BaseMapper<T, D, R> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody D dto) {
        try {
            T created = service.create(mapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to create resource.");
        }
    }

    @GetMapping
    public ResponseEntity<List<R>> findAll() {
        return ResponseEntity.ok(service.findAllDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findDtoById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody D dto) {
        try {
            T updated = service.update(id, mapper.toEntity(dto));
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
