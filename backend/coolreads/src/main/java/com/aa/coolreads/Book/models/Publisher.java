package com.aa.coolreads.Book.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Publisher implements Serializable {

    @Id
    private String name;

    private String email;

    private String siteUrl;

    private String logoUrl;

    public Publisher(){}

    public Publisher(String name, String email, String siteUrl, String logoUrl) {
        this.name = name;
        this.email = email;
        this.siteUrl = siteUrl;
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
