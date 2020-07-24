package com.example.movies.service;

import com.example.movies.domain.Rating;
import com.example.movies.service.dto.RatingDTO;

public interface RatingService {

    Rating save(RatingDTO rating);

    void deleteRating(Rating rating);
}
