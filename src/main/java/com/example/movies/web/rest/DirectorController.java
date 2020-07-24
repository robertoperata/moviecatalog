package com.example.movies.web.rest;

import com.example.movies.domain.Director;
import com.example.movies.service.DirectorService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DirectorController {

    private static final String ENTITY_NAME = "director";
    private final Logger log = LoggerFactory.getLogger(DirectorController.class);
    private final DirectorService directorService;

    @Value("${spring.application.name}")
    private String applicationName;

    public DirectorController(DirectorService directorService) {

        this.directorService = directorService;
    }

    /**
     * {@code GET /api/director}: get all directors
     * @return the {@link ResponseEntity} with the status {@code 200 (OK)} and the list of directors in body
     */
    @GetMapping("/director")
    public ResponseEntity<Collection<Director>> findAllDirectors() {
        log.debug("REST request to get all director");
        Collection<Director> directors = directorService.findAll();
        return ResponseEntity.ok().body(directors);
    }

    /**
     * {@code GET /api/director/:id}: get a director by id
     * @param id the id of the Director to retrieve
     * @return the {@link ResponseEntity} with the status {@code 200 (OK)} and the director in body response
     */
    @GetMapping("/director/{id}")
    public ResponseEntity<Director> getDirector(@PathVariable Long id) {
        log.debug("REST request to get director: {}", id);
        Optional<Director> director = directorService.findDirectorById(id);
        return ResponseUtil.wrapOrNotFound(director);
    }

    /**
     * {@code POST /api/director}: create a new director
     * @param director the director to be saved
     * @return the {@link ResponseEntity} with the status {@code 201 (Created)} and the Director in the body response,
     * or with status {@code 400 (Bad Request)} if the director has an id.
     * @throws URISyntaxException if the URI is not valid
     */
    @PostMapping("/director")
    public ResponseEntity<Director> savedirector(@Valid @RequestBody Director director)
        throws URISyntaxException {
        log.debug("REST request to save Director entity: {}", director);
        if(director.getId() != null) {
            throw  Problem.valueOf(Status.BAD_REQUEST, "Director already exists");
        }
        Director saved = directorService.save(director);
        return ResponseEntity.created(new URI("/api/director/" + saved.getId()))
                             .headers(HeaderUtil.createAlert(applicationName, ENTITY_NAME, saved.getId().toString())).body(saved);
    }

    /**
     * {@code PUT /api/director}: update an existing Director
     * @param director the Director to update
     * @return the {@link ResponseEntity} with the status {@code 200 (OK)} and the updated Director in the body response,
     * or with status {@code 400 (Bad Request)}  if the director it's not valid,  or with status {@code 500 (Internal Server Error)} if the Director couldn't be updated.
     * @throws URISyntaxException if the URI is not valid
     */
    @PutMapping("/director")
    public ResponseEntity<Director> updateDirector(@Valid @RequestBody Director director)
        throws URISyntaxException {
        log.debug("REST request to update director: {}", director);
        if(director.getId() == null) {
            throw  Problem.valueOf(Status.BAD_REQUEST, "Director id is null");
        }
        Director saved = directorService.save(director);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert(applicationName,  ENTITY_NAME, saved.getId().toString())).body(saved);
    }

    /**
     * {@code POST /api/director/:id}: delete the "id" Director
     * @param id the id of the Director entity
     * @return the {@link ResponseEntity} with the status {@code 204 (NO_CONTENT)}
     */
    @DeleteMapping("/director/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id){
        log.debug("REST request to delete director with id: {}", id);
        directorService.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}
