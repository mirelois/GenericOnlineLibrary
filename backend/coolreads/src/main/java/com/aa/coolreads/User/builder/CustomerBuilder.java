package com.aa.coolreads.User.builder;

import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class CustomerBuilder implements UserBuilder {
    private Customer customer;

    public CustomerBuilder() {
        this.reset();
    }

    public void reset(){
        this.customer = new Customer();
    }

    public Customer build(){
        return this.customer;
    }

    public CustomerBuilder setUsername(String username){
        this.customer.setUsername(username);
        return this;
    }

    public CustomerBuilder setPassword(String password){
        this.customer.setPassword(password);
        return this;
    }

    public CustomerBuilder setEmail(String email){
        this.customer.setEmail(email);
        return this;
    }

    public CustomerBuilder setName(String name){
        this.customer.setName(name);
        return this;
    }

    public CustomerBuilder setGender(String gender){
        this.customer.setGender(gender);
        return this;
    }

    public CustomerBuilder setPronouns(String pronouns){
        this.customer.setPronouns(pronouns);
        return this;
    }

    public CustomerBuilder setBirthDate(Date birthDate){
        this.customer.setBirthDate(birthDate);
        return this;
    }

    public CustomerBuilder setCountry(String country){
        this.customer.setCountry(country);
        return this;
    }

    public CustomerBuilder setDescription(String description){
        this.customer.setDescription(description);
        return this;
    }

    public CustomerBuilder setInterests(String interests){
        this.customer.setInterests(interests);
        return this;
    }

    public CustomerBuilder setProfileImageUrl(String profileImageUrl){
        this.customer.setProfileImageUrl(profileImageUrl);
        return this;
    }

    public CustomerBuilder setProfileBannerUrl(String profileBannerUrl){
        this.customer.setProfileBannerUrl(profileBannerUrl);
        return this;
    }

    public CustomerBuilder setFavoriteGenres(Set<Genre> favoriteGenres){
        this.customer.setFavoriteGenres(favoriteGenres);
        return this;
    }

    public CustomerBuilder setBookshelves(Set<Bookshelf> bookshelves){
        this.customer.setBookshelves(bookshelves);
        return this;
    }
}
