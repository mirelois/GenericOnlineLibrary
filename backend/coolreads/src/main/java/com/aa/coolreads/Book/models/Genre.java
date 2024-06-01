package com.aa.coolreads.Book.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Genre implements Serializable{

    @Id
    @Column(unique = true, nullable = false)
    private String genreType;

    public Genre(){}

    public Genre(String genreType) {
        this.genreType = genreType;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }
}


