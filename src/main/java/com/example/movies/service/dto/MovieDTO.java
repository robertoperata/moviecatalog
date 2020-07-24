package com.example.movies.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private List<Long> directorsIds;
    private List<Long> ratingsIds;

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

    public List<Long> getDirectorsIds() {

        return directorsIds;
    }

    public void setDirectorsIds(List<Long> directorsIds) {

        this.directorsIds = directorsIds;
    }

    public List<Long> getRatingsIds() {

        return ratingsIds;
    }

    public void setRatingsIds(List<Long> ratingsIds) {

        this.ratingsIds = ratingsIds;
    }

    @Override
    public String toString() {

        return "MovieDTO{" + "id=" + id + ", title='" + title + '\'' + ", directorsIds=" + directorsIds + ", ratingsIds=" + ratingsIds + '}';
    }
}
