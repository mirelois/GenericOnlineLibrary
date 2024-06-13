package com.aa.coolreads.User.builder;

import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.CustomerProfileDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

public class CustomerBuilder implements UserBuilder {
    private Customer customer;

    private CustomerProfileDetails profileDetails;

    public CustomerBuilder() {
        this.reset();
    }

    public void reset(){
        this.customer = new Customer();
        this.profileDetails = new CustomerProfileDetails();
    }

    public Customer build(){
        Customer customerBuilt = this.customer;
        customerBuilt.setProfileDetails(this.profileDetails);
        this.reset();
        return customerBuilt;
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
        this.profileDetails.setName(name);
        return this;
    }

    public CustomerBuilder setGender(String gender){
        this.profileDetails.setGender(gender);
        return this;
    }

    public CustomerBuilder setPronouns(String pronouns){
        this.profileDetails.setPronouns(pronouns);
        return this;
    }

    public CustomerBuilder setBirthDate(Date birthDate){
        this.profileDetails.setBirthDate(birthDate);
        return this;
    }

    public CustomerBuilder setCountry(String country){
        this.profileDetails.setCountry(country);
        return this;
    }

    public CustomerBuilder setDescription(String description){
        this.profileDetails.setDescription(description);
        return this;
    }

    public CustomerBuilder setInterests(String interests){
        this.profileDetails.setInterests(interests);
        return this;
    }

    public CustomerBuilder setProfileImageUrl(String profileImageUrl){
        this.profileDetails.setProfileImageUrl(profileImageUrl);
        return this;
    }

    public CustomerBuilder setProfileBannerUrl(String profileBannerUrl){
        this.profileDetails.setProfileBannerUrl(profileBannerUrl);
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
