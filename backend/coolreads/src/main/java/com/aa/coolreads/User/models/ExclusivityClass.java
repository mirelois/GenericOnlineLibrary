package com.aa.coolreads.User.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ExclusivityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="bookshelf_id", referencedColumnName="id")
    private Set<Bookshelf> bookshelves;

    @ManyToOne
    @JoinColumn(name="customer_username", referencedColumnName = "username", nullable = false)
    private Customer customer;

    public ExclusivityClass() {
        this.bookshelves = new HashSet<>();
    }

    public ExclusivityClass(String name, Customer customer) {
        this.name = name;
        this.customer = customer;
        this.bookshelves = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExclusivityClass that = (ExclusivityClass) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(bookshelves, that.bookshelves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bookshelves);
    }

    public Set<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<Bookshelf> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
