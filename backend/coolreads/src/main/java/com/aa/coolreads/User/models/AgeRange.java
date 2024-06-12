package com.aa.coolreads.User.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class AgeRange implements Serializable {
    @Id
    private String ageClass;
}
