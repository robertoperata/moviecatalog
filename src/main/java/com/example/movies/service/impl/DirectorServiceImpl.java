package com.example.movies.service.impl;

import com.example.movies.domain.Director;
import com.example.movies.repository.DirectorRepository;
import com.example.movies.service.DirectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final Logger log = LoggerFactory.getLogger(DirectorServiceImpl.class);
    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {

        this.directorRepository = directorRepository;
    }

    /**
     * Save a Director
     *
     * @param director
     * @return the saved director
     */
    @Override
    public Director save(Director director) {

        log.debug("request to save a director entity: {}", director);
        return directorRepository.save(director);
    }

    /**
     * Find a directro by id
     *
     * @param id must not be null
     * @return optional director
     */
    @Override
    public Optional<Director> findDirectorById(Long id) {

        log.debug("request to find a director entity by id: {}", id);
        return directorRepository.findById(id);
    }

    /**
     * Find all directors
     *
     * @return a list of directors
     */
    @Override
    public Collection<Director> findAll() {

        log.debug("request to find all director entities");
        return directorRepository.findAll();
    }

    /**
     * Delete a director
     *
     * @param director
     */
    @Override
    public void deleteById(Long id) {

        log.debug("request to delete a director entity: {}", id);
        directorRepository.deleteById(id);
    }

    @Override
    public Collection<Director> findAllByIdIn(List<Long> ids) {
        log.debug("request to find all directors by id in: {}", ids);
        return directorRepository.findAllByIdIn(ids);
    }
}
