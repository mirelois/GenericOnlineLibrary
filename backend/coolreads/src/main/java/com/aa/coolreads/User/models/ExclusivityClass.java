package com.aa.coolreads.User.models;

import jakarta.persistence.*;

@Entity
public class ExclusivityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bookshelf_id", referencedColumnName="id")
    private Bookshelf bookshelf;

    public ExclusivityClass() {}

    public ExclusivityClass(String name, Bookshelf bookshelf) {
        this.name = name;
        this.bookshelf = bookshelf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }
}
