package com.example.movies.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityMapper<E, T> {

    E toEntity(T t);

    T toDto(E e);

    default List<E> toEntity(List<T> t){
        return t.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default List<T> toDto(List<E> t){
        return t.stream().map(this::toDto).collect(Collectors.toList());
    }



}
