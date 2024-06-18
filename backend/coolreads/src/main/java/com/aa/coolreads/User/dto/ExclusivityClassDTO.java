package com.aa.coolreads.User.dto;

import java.util.Set;

public class ExclusivityClassDTO {
    private String name;

    private Set<String> bookshelves;

    public ExclusivityClassDTO(){}

    public ExclusivityClassDTO(String name, Set<String> bookshelves) {
        this.name = name;
        this.bookshelves = bookshelves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<String> bookshelves) {
        this.bookshelves = bookshelves;
    }
}
