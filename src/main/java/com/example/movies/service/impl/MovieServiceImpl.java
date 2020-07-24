package com.example.movies.service.impl;

import com.example.movies.domain.Director;
import com.example.movies.domain.Movie;
import com.example.movies.domain.Rating;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.DirectorService;
import com.example.movies.service.MovieService;
import com.example.movies.service.RatingService;
import com.example.movies.service.dto.DirectorDTO;
import com.example.movies.service.dto.MovieDTO;
import com.example.movies.service.dto.RatingDTO;
import com.example.movies.service.mapper.DirectorMapper;
import com.example.movies.service.mapper.MovieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MovieServiceImpl implements MovieService {
    private final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final DirectorService directorService;
    private final MovieMapper movieMapper;
    private final DirectorMapper directorMapper;
    private final RatingService ratingService;

    public MovieServiceImpl(MovieRepository movieRepository, DirectorService directorService, MovieMapper movieMapper, DirectorMapper directorMapper,
        RatingService ratingService) {
        this.movieRepository = movieRepository;
        this.directorService = directorService;
        this.movieMapper = movieMapper;
        this.directorMapper = directorMapper;
        this.ratingService = ratingService;
    }

    /**
     * Save a Movie entity
     * @param movieDTO
     * @return movieDTO
     */
    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        log.debug("request to save movieDTO entity: {}", movieDTO);
        Movie movie = movieMapper.toEntity(movieDTO);
        if(!CollectionUtils.isEmpty(movieDTO.getDirectorsIds())) {
            Collection<Director> directors = directorService.findAllByIdIn(movieDTO.getDirectorsIds());
            movie.setDirectors(new HashSet<>(directors));
        }
        return movieMapper.toDto(movieRepository.save(movie));
    }

    /**
     * Find a movie by id
     * @param id must not be null
     * @return Optional of Movie
     */
    @Override
    @Transactional(readOnly = true)
    public MovieDTO findMovieById(Long id) {
        log.debug("request to find movie entity by id: {}", id);
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(!movieOptional.isPresent()){
            throw Problem.valueOf(Status.BAD_REQUEST, "Movie already exists");
        }
        return movieMapper.toDto(movieOptional.get());
    }

    /**
     * find all movies
     * @return all movies
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<MovieDTO> findAll() {
        log.debug("request to find all movies");
        return movieMapper.toDto(movieRepository.findAll());
    }

    /**
     * Delete a movie
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        log.debug("request to delete movie entity: {}", id);
        movieRepository.deleteById(id);
    }

    /**
     * Get all movies above a rating
     * @param rating
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<MovieDTO> getMoviesAboveRating(Integer rating) {
        log.debug("request to get all movies above rating: {}", rating);
        Collection<Movie> moviesAboveRating = movieRepository.getMoviesAboveRating(rating);
        return movieMapper.toDto(new ArrayList<>(moviesAboveRating));
    }

    /**
     * Get all movies by director
     * @param directorDTO
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<MovieDTO> findAllByDirector(DirectorDTO directorDTO) {
        log.debug("request to find all movies by director: {}", directorDTO);
        Optional<Director> director = directorService.findDirectorById(directorDTO.getId());
        if(!director.isPresent()){
            throw Problem.valueOf(Status.BAD_REQUEST, "Director doesn't exists");
        }
        Collection<Movie> movies = movieRepository.findAllByDirectors(director.get());
        return movieMapper.toDto(new ArrayList<>(movies));
    }

    @Override
    public MovieDTO addRating(Long movieId, RatingDTO ratingDTO) {
        log.debug("request to add rating [{}]  to movie with id: {}",ratingDTO, movieId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(!optionalMovie.isPresent()) {
            throw Problem.valueOf(Status.BAD_REQUEST, "Movie id not valid");
        }
        Rating rating = ratingService.save(ratingDTO);
        Movie movie = optionalMovie.get();
        movie.addRating(rating);
        movie = movieRepository.save(movie);
        return movieMapper.toDto(movie);
    }
}
