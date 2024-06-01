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

    @ManyToOne
    private PublisherLocation publisherLocation;

    public Publisher(){}

    public Publisher(String name, String email, String siteUrl, String logoUrl, PublisherLocation publisherLocation) {
        this.name = name;
        this.email = email;
        this.siteUrl = siteUrl;
        this.logoUrl = logoUrl;
        this.publisherLocation = publisherLocation;
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

    public PublisherLocation getPublisherLocation() {
        return publisherLocation;
    }

    public void setPublisherLocation(PublisherLocation publisherLocation) {
        this.publisherLocation = publisherLocation;
    }
}
