package com.aa.coolreads.User.models;

import com.aa.coolreads.Book.models.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;

@Entity
public class PersonalBook implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer pagesRead;

    private Date insertDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Book book;

    public PersonalBook(){}

    public PersonalBook(Integer pagesRead, Date insertDate, Customer customer, Book book) {
        this.pagesRead = pagesRead;
        this.insertDate = insertDate;
        this.customer = customer;
        this.book = book;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
