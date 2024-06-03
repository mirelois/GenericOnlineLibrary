package com.aa.coolreads.User.models;

import com.aa.coolreads.Book.models.Book;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bookshelf implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated
    private Privacy privacy;

    @OneToMany
    private Set<PersonalBook> personalBooks;

    public Bookshelf(){}

    public Bookshelf(String name, Privacy privacy) {
        this.name = name;
        this.privacy = privacy;
        this.personalBooks = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public Set<PersonalBook> getPersonalBooks() {
        return personalBooks;
    }

    public void setPersonalBooks(Set<PersonalBook> personalBooks) {
        this.personalBooks = personalBooks;
    }
}
