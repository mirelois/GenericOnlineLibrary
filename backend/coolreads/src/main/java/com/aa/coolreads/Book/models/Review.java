package com.aa.coolreads.Book.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private Date PostDate;

    //private Set<ReviewLike> reviewLikes;
}
