package com.example.movies.service.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private Integer rating;
    private String comment;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Integer getRating() {

        return rating;
    }

    public void setRating(Integer rating) {

        this.rating = rating;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }

    @Override
    public String toString() {

        return "RatingDTO{" + "id=" + id + ", rating=" + rating + ", comment='" + comment + '\'' + '}';
    }
}
