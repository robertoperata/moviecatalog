package com.example.movies.web.rest;

import com.example.movies.domain.Movie;
import com.example.movies.service.MovieService;
import com.example.movies.service.dto.MovieDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private List<MovieDTO> movies = new ArrayList<>();

    @BeforeEach
    void setup() {
        MovieDTO movie1 = new MovieDTO();
        movie1.setId(1L);
        movie1.setTitle("title 1");
        MovieDTO movie2 = new MovieDTO();
        movie2.setId(2L);
        movie2.setTitle("title 2");
        MovieDTO movie3 = new MovieDTO();
        movie3.setId(3L);
        movie3.setTitle("title 3");
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
    }

    @Test
    public void test_findAllMovie()
        throws Exception {
        given(movieService.findAll()).willReturn(movies);

        this.mockMvc.perform(get("/api/movie"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(movies.size())));
    }

}
