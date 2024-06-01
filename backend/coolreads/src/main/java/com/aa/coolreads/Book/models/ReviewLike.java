package com.aa.coolreads.Book.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ReviewLike {
    @Id
    @GeneratedValue
    private int id;

    private double rating;

    //private User user;
}
