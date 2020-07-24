package com.example.movies.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer rating;

    private String comment;

    @ManyToOne
    private Movie movie;

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

    public Movie getMovie() {

        return movie;
    }

    public void setMovie(Movie movie) {
        if(this.movie.getRatings() != null) {
            this.movie.getRatings().remove(this);
        }
        this.movie = movie;
        if(movie != null) {
            movie.getRatings().add(this);
        }
    }
}
