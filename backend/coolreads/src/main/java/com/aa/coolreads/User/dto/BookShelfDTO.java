package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.Privacy;

import java.util.Set;

public class BookShelfDTO {
    private String name;

    private Privacy privacy;

    //private Set<Book> books;

    public BookShelfDTO(){}

    public BookShelfDTO(String name, Privacy privacy) {
        this.name = name;
        this.privacy = privacy;
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
}
