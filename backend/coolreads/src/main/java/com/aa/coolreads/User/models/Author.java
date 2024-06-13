package com.aa.coolreads.User.models;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Genre;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@DiscriminatorValue("author")
public class Author extends Customer implements Serializable{

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Book> booksCreated;

    public Author(){super();}

    public Set<Book> getBooksCreated() {
        return booksCreated;
    }

    public void setBooksCreated(Set<Book> booksCreated) {
        this.booksCreated = booksCreated;
    }
}
