package com.example.movies.service.impl;

import com.example.movies.domain.Movie;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.dto.MovieDTO;
import com.example.movies.service.mapper.MovieMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MovieServiceImplTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Autowired
    private MovieMapper movieMapper;

//    @Test
//    void test_save_movie() {
//        Movie movie = new Movie();
//        movie.setTitle("title");
//        given(movieRepository.save(movie)).willAnswer(saved -> saved.getArgument(0));
//
//        MovieDTO savedMovie = movieService.save(movieMapper.toDto(movie));
//        assertThat(savedMovie).isNotNull();
//        verify(movieRepository).save(any(Movie.class));
//    }



//    @Test
//    void test_findAll() {
//        List<MovieDTO> movieList = new ArrayList<>();
//        MovieDTO movie1 = new MovieDTO();
//        movie1.setTitle("title 1");
//        MovieDTO movie2 = new MovieDTO();
//        movie2.setTitle("title 2");
//        MovieDTO movie3 = new MovieDTO();
//        movie3.setTitle("title 3");
//        movieList.add(movie1);
//        movieList.add(movie2);
//        movieList.add(movie3);
//
//        given(movieRepository.findAll()).willReturn(movieMapper.toEntity(movieList));
//        Collection<MovieDTO> expected = movieService.findAll();
//        assertEquals(movieList, expected);
//    }

//    @Test
//    void test_delete() {
//        final long id = 1L;
//        Movie movie1 = new Movie();
//        movie1.setTitle("title 1");
//        movieService.deleteById(movie1.getId());
//        verify(movieRepository, times(1)).delete(movie1);
//    }
}
