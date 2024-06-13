package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.Gender;

import java.util.Date;

public class SimpleCustomerDTO {

    private String name;

    private Gender gender;

    private String pronouns;

    private Date birthDate;

    private String country;

    private String description;

    private String interests;

    private String profileImageUrl;

    private String profileBannerUrl;

    private BookShelfDTO highlightedBookshelf;

<<<<<<< HEAD:backend/coolreads/src/main/java/com/aa/coolreads/User/dto/NewCustomerDTO.java
    public NewCustomerDTO(String username, String password, String email, String name, Gender gender, String pronouns, Date birthDate, String country, String description, String interests, String profileImageUrl, String profileBannerUrl) {
        this.username = username;
        this.password = password;
        this.email = email;
=======
    public SimpleCustomerDTO(){}

    public SimpleCustomerDTO(String name, String gender, String pronouns, Date birthDate, String country, String description, String interests, String profileImageUrl, String profileBannerUrl) {
>>>>>>> main:backend/coolreads/src/main/java/com/aa/coolreads/User/dto/SimpleCustomerDTO.java
        this.name = name;
        this.gender = gender;
        this.pronouns = pronouns;
        this.birthDate = birthDate;
        this.country = country;
        this.description = description;
        this.interests = interests;
        this.profileImageUrl = profileImageUrl;
        this.profileBannerUrl = profileBannerUrl;
    }

    public SimpleCustomerDTO(String name, String gender, String pronouns, Date birthDate, String country, String description, String interests, String profileImageUrl, String profileBannerUrl, BookShelfDTO highlightedBookshelf) {
        this.name = name;
        this.gender = gender;
        this.pronouns = pronouns;
        this.birthDate = birthDate;
        this.country = country;
        this.description = description;
        this.interests = interests;
        this.profileImageUrl = profileImageUrl;
        this.profileBannerUrl = profileBannerUrl;
        this.highlightedBookshelf = highlightedBookshelf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public void setProfileBannerUrl(String profileBannerUrl) {
        this.profileBannerUrl = profileBannerUrl;
    }

    public BookShelfDTO getHighlightedBookshelf() {
        return highlightedBookshelf;
    }

    public void setHighlightedBookshelf(BookShelfDTO highlightedBookshelf) {
        this.highlightedBookshelf = highlightedBookshelf;
    }
}
