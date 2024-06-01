package com.aa.coolreads.User.models;

import com.aa.coolreads.Book.models.Book;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Bookshelf implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Boolean isPublic;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "PersonalBook",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "isbn") }
    )
    private Set<Book> books;

    public Bookshelf(){}

    public Bookshelf(String name, Boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
