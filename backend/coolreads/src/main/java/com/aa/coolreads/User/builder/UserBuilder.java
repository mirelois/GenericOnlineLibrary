package com.aa.coolreads.User.builder;

import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.User.models.Bookshelf;

import java.util.Date;
import java.util.Set;

public interface UserBuilder {
    void reset();

    Object build();

    UserBuilder setUsername(String username);

    UserBuilder setPassword(String password);

    UserBuilder setEmail(String email);

    UserBuilder setName(String name);

    UserBuilder setGender(String gender);

    UserBuilder setPronouns(String pronouns);

    UserBuilder setBirthDate(Date birthDate);

    UserBuilder setCountry(String country);

    UserBuilder setDescription(String description);

    UserBuilder setInterests(String interests);

    UserBuilder setProfileImageUrl(String profileImageUrl);

    UserBuilder setProfileBannerUrl(String profileBannerUrl);

    UserBuilder setBookshelves(Set<Bookshelf> bookshelves);
}
