package com.example.movies.service.mapper;

import com.example.movies.domain.Rating;
import com.example.movies.service.dto.RatingDTO;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper implements EntityMapper<Rating, RatingDTO> {

    @Override
    public Rating toEntity(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        rating.setId(ratingDTO.getId());
        rating.setRating(ratingDTO.getRating());
        rating.setComment(ratingDTO.getComment());
        return rating;
    }

    @Override
    public RatingDTO toDto(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setComment(rating.getComment());
        ratingDTO.setRating(rating.getRating());
        return ratingDTO;
    }
}
