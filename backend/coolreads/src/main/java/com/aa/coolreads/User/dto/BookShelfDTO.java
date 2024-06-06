package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.Privacy;

import java.util.Set;

public class BookShelfDTO extends BookShelfCreationDTO{

    private Set<PersonalBookDTO> personalBooks;

    public BookShelfDTO(){}

    public BookShelfDTO(String name, String privacy, Set<PersonalBookDTO> personalBooks) {
        super(name, privacy);
        this.personalBooks = personalBooks;
    }

    public Set<PersonalBookDTO> getPersonalBooks() {
        return personalBooks;
    }

    public void setPersonalBooks(Set<PersonalBookDTO> personalBooks) {
        this.personalBooks = personalBooks;
    }
}
