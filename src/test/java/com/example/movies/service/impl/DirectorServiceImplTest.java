package com.example.movies.service.impl;

import com.example.movies.domain.Director;
import com.example.movies.domain.Movie;
import com.example.movies.repository.DirectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DirectorServiceImplTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorServiceImpl directorService;

    @Test
    void test_save_movie() {

        Director director = new Director();
        director.setFirstName("name");
        director.setLastName("last name");
        given(directorRepository.save(director)).willAnswer(saved -> saved.getArgument(0));

        Director expected = directorService.save(director);
        assertThat(expected).isNotNull();
        verify(directorRepository).save(any(Director.class));
    }

    @Test
    void test_findDirectorById() {
        final Long id = 1L;
        final Director director = new Director();
        director.setFirstName("name");
        director.setLastName("last name");
        given(directorRepository.findById(id)).willReturn(Optional.of(director));
        final Optional<Director> found = directorService.findDirectorById(id);
        assertThat(found).isNotNull();
        assertEquals("name", found.get().getFirstName());
    }

    @Test
    void test_findAll() {
        List<Director> directors = new ArrayList<>();
        Director director1 = new Director();
        director1.setFirstName("name");
        director1.setLastName("last name");
        Director director2 = new Director();
        director2.setFirstName("name");
        director2.setLastName("last name");
        Director director3 = new Director();
        director3.setFirstName("name");
        director3.setLastName("last name");
        directors.add(director1);
        directors.add(director2);
        directors.add(director3);

        given(directorRepository.findAll()).willReturn(directors);
        Collection<Director> expected = directorService.findAll();
        assertEquals(directors, expected);
    }

    @Test
    void test_delete() {
        final long id = 1L;
        Director director = new Director();
        director.setFirstName("name");
        directorService.deleteById(director.getId());
        verify(directorRepository, times(1)).delete(director);
    }
}
