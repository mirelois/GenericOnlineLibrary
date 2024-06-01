package com.aa.coolreads.User.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class User {
    @Id
    private String username;

    private String password;

    private String email;

    private String name;

    private String Gender;

    private String Pronouns;

    private Date birthDate;

    private String country;

    private String description;

    private String interests;

    private String profileImageUrl;

    private String profileBannerUrl;

    public User(){}

    public User(String username, String password, String email, String name, String gender, String pronouns, Date birthDate, String country, String description, String interests, String profileImageUrl, String profileBannerUrl) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        Gender = gender;
        Pronouns = pronouns;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPronouns() {
        return Pronouns;
    }

    public void setPronouns(String pronouns) {
        Pronouns = pronouns;
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
