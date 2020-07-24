package com.example.movies.repository;

import com.example.movies.domain.Director;
import com.example.movies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    void deleteById(Long id);

    @Query("select m, avg(r.rating) as average from Movie m, Rating r where  average > :rating")
    Collection<Movie> getMoviesAboveRating(Integer rating);

    Collection<Movie> findAllByDirectors(Director director);
}
