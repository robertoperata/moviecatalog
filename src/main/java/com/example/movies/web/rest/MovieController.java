package com.example.movies.web.rest;

import com.example.movies.domain.Director;
import com.example.movies.domain.Movie;
import com.example.movies.domain.Rating;
import com.example.movies.service.MovieService;
import com.example.movies.service.dto.DirectorDTO;
import com.example.movies.service.dto.MovieDTO;
import com.example.movies.service.dto.RatingDTO;
import com.example.movies.web.rest.util.HeaderUtil;
import com.example.movies.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    private static final String ENTITY_NAME = "movie";
    private final Logger log = LoggerFactory.getLogger(MovieController.class);
    private final MovieService movieService;

    @Value("${spring.application.name}")
    private String applicationName;

    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    /**
     * {@code GET /api/movie}: get all the movies
     * @return the {@link ResponseEntity} with the status {@code 200 (OK)} and the list of movies in body
     */
    @GetMapping("/movie")
    public ResponseEntity<Collection<MovieDTO>> findAllMovies() {
      log.debug("REST request to get all movies");
      Collection<MovieDTO> movies = movieService.findAll();
       return ResponseEntity.ok().body(movies);
    }

    /**
     * {@code GET /api/movie/:id}: get a movie by id
     * @param id the id of the movie to retrieve
     * @return the {@link ResponseEntity} with the status {@code 200 (OK)} and the movie in body
     */
    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        log.debug("REST request to get Movie: {}", id);
        MovieDTO movie = movieService.findMovieById(id);
        return  ResponseEntity.ok().body(movie);
    }

    /**
     * {@code POST /api/movieDTO}: create a new movieDTO
     * @param movieDTO the movieDTO to be saved
     * @return the {@link ResponseEntity} with the status {@code 201 (Created)} and the new movieDTO in the body, or with status {@code 400 (Bad Request)} if the movieDTO has an id.
     * @throws URISyntaxException
     */
    @PostMapping("/movie")
    public ResponseEntity<MovieDTO> saveMovie(@Valid @RequestBody MovieDTO movieDTO)
        throws URISyntaxException {
        log.debug("REST request to save Movie: {}", movieDTO);
        if(movieDTO.getId() != null) {
            throw  Problem.valueOf(Status.BAD_REQUEST, "Movie already exists");
        }
        MovieDTO saved = movieService.save(movieDTO);
        return ResponseEntity.created(new URI("/api/movieDTO/" + saved.getId()))
                             .headers(HeaderUtil.createAlert(applicationName,  ENTITY_NAME, saved.getId().toString())).body(saved);
    }

    /**
     * {@code PUT /api/movieDTO}: update an existing movieDTO
     * @param movieDTO the movieDTO to update
     * @return the {@link ResponseEntity} with the status {@code 200 (OK)} and the updated movieDTO in the body,
     * or with status {@code 400 (Bad Request)}  if the movieDTO it's not valid,  or with status {@code 500 (Internal Server Error)} if the movieDTO couldn't be updated.
     * @throws URISyntaxException if the location URI is not valid
     */
    @PutMapping("/movie")
    public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO movieDTO)
        throws URISyntaxException {
        log.debug("REST request to update Movie: {}", movieDTO);
        if(movieDTO.getId() == null) {
            throw  Problem.valueOf(Status.BAD_REQUEST, "Movie id is null");
        }
        MovieDTO saved = movieService.save(movieDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert(applicationName,  ENTITY_NAME, saved.getId().toString())).body(saved);
    }

    /**
     * {@code POST /api/movie/:id}: delete the "id" movie
     * @param id the id of the movie
     * @return the {@link ResponseEntity} with the status {@code 204 (NO_CONTENT)}
     */
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        log.debug("REST request to delete movie with id: {}", id);
        movieService.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    @PostMapping("/movie/{id}/rating")
    public ResponseEntity<MovieDTO> addRatingToMovie(@PathVariable Long id, RatingDTO ratingDTO) {
        log.debug("REST request to add rating to a movie");

        MovieDTO movieDTO = movieService.addRating(id, ratingDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert(applicationName, ENTITY_NAME, movieDTO.getId().toString())).body(movieDTO);
    }

    @GetMapping("/movie/rating/{rating}")
    public ResponseEntity<Collection<MovieDTO>> getMoviesAboveRating(@PathVariable Integer rating) {
        log.debug("REST request to get movies above rating: {}", rating);
        Collection<MovieDTO> moviesAboveRating = movieService.getMoviesAboveRating(rating);
        return ResponseEntity.ok().body(moviesAboveRating);
    }

    @GetMapping("/movie/director")
    public ResponseEntity<Collection<MovieDTO>> getMoviesByDirector(@RequestBody DirectorDTO directorDTO) {
        log.debug("REST request to get movies by Director: {}", directorDTO);
        Collection<MovieDTO> moviesAboveRating = movieService.findAllByDirector(directorDTO);
        return ResponseEntity.ok().body(moviesAboveRating);
    }



}
