package com.aa.coolreads.User.models;

import com.aa.coolreads.Book.models.Book;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PersonalBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pagesRead;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date insertDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bookshelf_personalbook",
            joinColumns = @JoinColumn(name = "personalbook_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bookshelf_id", referencedColumnName = "id")
    )
    private Set<Bookshelf> bookshelves;

    @ManyToOne
    private Book book;

    @ManyToOne
    @JoinColumn(name="customer_username", referencedColumnName="username")
    private Customer customer;

    public PersonalBook(){
        this.bookshelves = new HashSet<>();
    }

    public PersonalBook(Integer pagesRead, Book book, Customer customer) {
        this.pagesRead = pagesRead;
        this.book = book;
        this.bookshelves = new HashSet<>();
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(Integer pagesRead) {
        this.pagesRead = pagesRead;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<Bookshelf> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public void addBookshelf(Bookshelf bookshelf){
        this.bookshelves.add(bookshelf);
    }

    public void removeBookshelf(Bookshelf bookshelf){
        this.bookshelves.remove(bookshelf);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
