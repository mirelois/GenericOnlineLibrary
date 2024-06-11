package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.Gender;

import java.util.Date;
import java.util.Set;

public class CustomerDTO extends NewCustomerDTO{

    private Set<String> favoriteGenres;

    private Set<BookShelfDTO> bookshelves;

    public CustomerDTO(){}

    public CustomerDTO(String username, String password, String email, String name, Gender gender, String pronouns, Date birthDate, String country, String description, String interests, String profileImageUrl, String profileBannerUrl, Set<String> favoriteGenres, Set<BookShelfDTO> bookshelves) {
        super(username, password, email, name, gender, pronouns, birthDate, country, description, interests, profileImageUrl, profileBannerUrl);
        this.favoriteGenres = favoriteGenres;
        this.bookshelves = bookshelves;
    }

    public Set<String> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setFavoriteGenres(Set<String> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public Set<BookShelfDTO> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<BookShelfDTO> bookshelves) {
        this.bookshelves = bookshelves;
    }
}
