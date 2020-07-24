package com.example.movies.service.mapper;

import com.example.movies.domain.Director;
import com.example.movies.domain.Movie;
import com.example.movies.domain.Rating;
import com.example.movies.service.dto.DirectorDTO;
import com.example.movies.service.dto.MovieDTO;
import com.example.movies.service.dto.RatingDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class MovieMapper implements EntityMapper<Movie, MovieDTO> {

    private final DirectorMapper directorMapper;
    private final RatingMapper ratingMapper;

    public MovieMapper(DirectorMapper directorMapper, RatingMapper ratingMapper) {

        this.directorMapper = directorMapper;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public Movie toEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        if(!CollectionUtils.isEmpty(movieDTO.getDirectorsIds())) {
            movieDTO.getDirectorsIds().stream().map((id) -> {
                Director dir = new Director();
                dir.setId(id);
                return dir;
            }).collect(Collectors.toList());
        }
        if(!CollectionUtils.isEmpty(movieDTO.getRatingsIds())) {
            movieDTO.getRatingsIds().stream().map((id) -> {
                Rating rating = new Rating();
                rating.setId(id);
                return rating;
            }).collect(Collectors.toList());
        }

        return movie;
    }

    @Override
    public MovieDTO toDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        if(!CollectionUtils.isEmpty(movie.getDirectors())) {
            movieDTO.setDirectorsIds( directorMapper.toDto(new ArrayList<>(movie.getDirectors()))
                                                    .stream()
                                                    .map(DirectorDTO::getId)
                                                    .collect(Collectors.toList()));

        }
        if(!CollectionUtils.isEmpty(movie.getRatings())) {
            movieDTO.setRatingsIds(ratingMapper.toDto(new ArrayList<>(movie.getRatings()))
                                               .stream()
                                               .map(RatingDTO::getId)
                                               .collect(Collectors.toList()));
        }

        return movieDTO;
    }
}
