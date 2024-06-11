package com.aa.coolreads.User.builder;

import com.aa.coolreads.User.dto.NewCustomerDTO;
import com.aa.coolreads.User.models.Gender;

import java.util.Date;

public class NewCustomerDTOBuilder {
    private NewCustomerDTO customer;

    public NewCustomerDTOBuilder() {
        this.reset();
    }

    public void reset(){
        this.customer = new NewCustomerDTO();
    }

    public NewCustomerDTO build(){
        return this.customer;
    }

    public NewCustomerDTOBuilder setUsername(String username){
        this.customer.setUsername(username);
        return this;
    }

    public NewCustomerDTOBuilder setPassword(String password){
        this.customer.setPassword(password);
        return this;
    }

    public NewCustomerDTOBuilder setEmail(String email){
        this.customer.setEmail(email);
        return this;
    }

    public NewCustomerDTOBuilder setName(String name){
        this.customer.setName(name);
        return this;
    }

    public NewCustomerDTOBuilder setGender(Gender gender){
        this.customer.setGender(gender);
        return this;
    }

    public NewCustomerDTOBuilder setPronouns(String pronouns){
        this.customer.setPronouns(pronouns);
        return this;
    }

    public NewCustomerDTOBuilder setBirthDate(Date birthDate){
        this.customer.setBirthDate(birthDate);
        return this;
    }

    public NewCustomerDTOBuilder setCountry(String country){
        this.customer.setCountry(country);
        return this;
    }

    public NewCustomerDTOBuilder setDescription(String description){
        this.customer.setDescription(description);
        return this;
    }

    public NewCustomerDTOBuilder setInterests(String interests){
        this.customer.setInterests(interests);
        return this;
    }

    public NewCustomerDTOBuilder setProfileImageUrl(String profileImageUrl){
        this.customer.setProfileImageUrl(profileImageUrl);
        return this;
    }

    public NewCustomerDTOBuilder setProfileBannerUrl(String profileBannerUrl){
        this.customer.setProfileBannerUrl(profileBannerUrl);
        return this;
    }
}
