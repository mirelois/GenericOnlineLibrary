package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.Privacy;

import java.util.Set;

public class BookShelfDTO {
    private String name;

    private Privacy privacy;

    private Set<PersonalBookDTO> personalBooks;

    public BookShelfDTO(){}

    public BookShelfDTO(String name, Privacy privacy, Set<PersonalBookDTO> personalBooks) {
        this.name = name;
        this.privacy = privacy;
        this.personalBooks = personalBooks;
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

    public Set<PersonalBookDTO> getPersonalBooks() {
        return personalBooks;
    }

    public void setPersonalBooks(Set<PersonalBookDTO> personalBooks) {
        this.personalBooks = personalBooks;
    }
}
