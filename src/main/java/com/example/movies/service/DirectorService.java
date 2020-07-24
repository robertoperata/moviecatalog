package com.example.movies.service;


import com.example.movies.domain.Director;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DirectorService {

    Director save(Director director);

    Optional<Director> findDirectorById(Long id);

    Collection<Director> findAll();

    void deleteById(Long id);

    Collection<Director> findAllByIdIn(List<Long > ids);
}
