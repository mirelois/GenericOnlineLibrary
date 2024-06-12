package com.aa.coolreads.User.builder;

import com.aa.coolreads.User.dto.BookShelfDTO;
import com.aa.coolreads.User.dto.SimpleCustomerDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SimpleDTOBuilder {
    private SimpleCustomerDTO customer;

    public SimpleDTOBuilder() {
        this.reset();
    }

    public void reset(){
        this.customer = new SimpleCustomerDTO();
    }

    public SimpleCustomerDTO build(){
        return this.customer;
    }

    public SimpleDTOBuilder setUsername(String username){
        this.customer.setUsername(username);
        return this;
    }

    public SimpleDTOBuilder setPassword(String password){
        this.customer.setPassword(password);
        return this;
    }

    public SimpleDTOBuilder setEmail(String email){
        this.customer.setEmail(email);
        return this;
    }

    public SimpleDTOBuilder setName(String name){
        this.customer.setName(name);
        return this;
    }

    public SimpleDTOBuilder setGender(String gender){
        this.customer.setGender(gender);
        return this;
    }

    public SimpleDTOBuilder setPronouns(String pronouns){
        this.customer.setPronouns(pronouns);
        return this;
    }

    public SimpleDTOBuilder setBirthDate(Date birthDate){
        this.customer.setBirthDate(birthDate);
        return this;
    }

    public SimpleDTOBuilder setCountry(String country){
        this.customer.setCountry(country);
        return this;
    }

    public SimpleDTOBuilder setDescription(String description){
        this.customer.setDescription(description);
        return this;
    }

    public SimpleDTOBuilder setInterests(String interests){
        this.customer.setInterests(interests);
        return this;
    }

    public SimpleDTOBuilder setProfileImageUrl(String profileImageUrl){
        this.customer.setProfileImageUrl(profileImageUrl);
        return this;
    }

    public SimpleDTOBuilder setProfileBannerUrl(String profileBannerUrl){
        this.customer.setProfileBannerUrl(profileBannerUrl);
        return this;
    }

    public SimpleDTOBuilder setHighlightedBookshelf(BookShelfDTO bookShelfDTO){
        this.customer.setHighlightedBookshelf(bookShelfDTO);
        return this;
    }
}
