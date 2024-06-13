package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.Gender;

import java.util.Date;

public class NewCustomerDTO {
    private String username;

    private String password;

    private String email;

    private String name;

    private Gender gender;

    private String pronouns;

    private Date birthDate;

    private String country;

    private String description;

    private String interests;

    private String profileImageUrl;

    private String profileBannerUrl;

    public NewCustomerDTO(){}

    public NewCustomerDTO(String username, String password, String email, String name, Gender gender, String pronouns, Date birthDate, String country, String description, String interests, String profileImageUrl, String profileBannerUrl) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
