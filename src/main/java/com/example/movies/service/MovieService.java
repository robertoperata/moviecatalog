package com.example.movies.service;

import com.example.movies.domain.Director;
import com.example.movies.domain.Movie;
import com.example.movies.domain.Rating;
import com.example.movies.service.dto.DirectorDTO;
import com.example.movies.service.dto.MovieDTO;
import com.example.movies.service.dto.RatingDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieDTO save(MovieDTO movie);

    MovieDTO findMovieById(Long id);

    Collection<MovieDTO> findAll();

    void deleteById(Long id);

    List<MovieDTO> getMoviesAboveRating(Integer rating);

    Collection<MovieDTO> findAllByDirector(DirectorDTO director);

    MovieDTO addRating(Long movieId, RatingDTO rating);
}
