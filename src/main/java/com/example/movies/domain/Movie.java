package com.example.movies.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private String title;

    @OneToMany(mappedBy = "movie")
    private Set<Rating> ratings = new HashSet<>();

    @ManyToMany
    private Set<Director> directors = new HashSet<>();

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public Set<Rating> getRatings() {

        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {

        this.ratings = ratings;
    }

    public void addRating(Rating rating) {
        this.getRatings().add(rating);
        this.ratings.add(rating);
    }

    public void removeRating(Rating rating) {
        this.getRatings().remove(rating);
        
    }

    public Set<Director> getDirectors() {

        return directors;
    }

    public void setDirectors(Set<Director> directors) {

        this.directors = directors;
    }
}
