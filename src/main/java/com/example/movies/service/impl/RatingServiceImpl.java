package com.example.movies.service.impl;

import com.example.movies.domain.Rating;
import com.example.movies.repository.RatingRepository;
import com.example.movies.service.RatingService;
import com.example.movies.service.dto.RatingDTO;
import com.example.movies.service.mapper.RatingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final Logger log = LoggerFactory.getLogger(RatingServiceImpl.class);
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {

        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    /**
     * Save a Rating
     * @param rating
     * @return
     */
    @Override
    public Rating save(RatingDTO rating) {
        log.debug("request to save rating: {}", rating);
        return ratingRepository.save(ratingMapper.toEntity(rating));
    }

    /**
     * Delete a rating
     * @param rating
     */
    @Override
    public void deleteRating(Rating rating) {
        log.debug("request to delete rating: {}", rating);
        ratingRepository.delete(rating);
    }
}
