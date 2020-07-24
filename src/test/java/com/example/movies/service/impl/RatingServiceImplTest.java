package com.example.movies.service.impl;

import com.example.movies.domain.Rating;
import com.example.movies.repository.RatingRepository;
import com.example.movies.service.mapper.RatingMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Autowired
    private RatingMapper ratingMapper;

//    @Test
    void test_save_rating() {
        Rating rating = new Rating();
        rating.setRating(3);
        given(ratingRepository.save(rating)).willAnswer(saved -> saved.getArgument(0));

        Rating savedRating = ratingService.save(ratingMapper.toDto(rating));
        assertThat(savedRating).isNotNull();
        verify(ratingRepository).save(any(Rating.class));
    }



//    @Test
    void test_delete() {
        final long id = 1L;
        Rating rating = new Rating();
        rating.setRating(3);
        ratingService.deleteRating(rating);
        verify(ratingRepository, times(1)).delete(rating);
    }
}
