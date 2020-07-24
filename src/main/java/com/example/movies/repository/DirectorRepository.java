package com.example.movies.repository;

import com.example.movies.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    void deleteById(Long id);

    Collection<Director> findAllByIdIn(List<Long> ids);
}
