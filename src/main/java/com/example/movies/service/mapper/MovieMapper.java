package com.example.movies.service.mapper;

import com.example.movies.domain.Movie;
import com.example.movies.service.dto.MovieDTO;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements EntityMapper<Movie, MovieDTO> {

    @Override
    public Movie toEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        return movie;
    }

    @Override
    public MovieDTO toDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(movie.getTitle());
        return movieDTO;
    }
}
