package com.aa.coolreads.Book.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Genre implements Serializable{

    @Id
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private GenreType genreType;

    public Genre(){}

    public Genre(GenreType genreType){
        this.genreType = genreType;
    }

    public GenreType getGenreType() {
        return genreType;
    }

    public void setGenreType(GenreType genreType) {
        this.genreType = genreType;
    }
}


