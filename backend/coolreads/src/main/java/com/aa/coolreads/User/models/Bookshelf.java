package com.aa.coolreads.User.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bookshelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bookshelf_personalbook",
            joinColumns = @JoinColumn(name = "bookshelf_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "personalbook_id", referencedColumnName = "id")
    )
    private Set<PersonalBook> personalBooks;

    @ManyToOne
    @JoinColumn(name="customer_username", referencedColumnName="username")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private ExclusivityClass exclusivityClass;

    public Bookshelf(){
        this.personalBooks = new HashSet<>();
    }

    public Bookshelf(String name, Privacy privacy, Customer customer) {
        this.name = name;
        this.privacy = privacy;
        this.personalBooks = new HashSet<>();
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public Set<PersonalBook> getPersonalBooks() {
        return personalBooks;
    }

    public void setPersonalBooks(Set<PersonalBook> personalBooks) {
        this.personalBooks = personalBooks;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ExclusivityClass getExclusivityClass() {
        return exclusivityClass;
    }

    public void setExclusivityClass(ExclusivityClass exclusivityClass) {
        this.exclusivityClass = exclusivityClass;
    }
}
