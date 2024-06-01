package com.aa.coolreads.Book.exception;

import java.util.Set;

public class GenresNotFoundException extends Exception{
    public GenresNotFoundException(Set<String> genreNames) {
        super("Some of Genres given are Invalid! They are:\n" + genreNames.toString());
    }
}
