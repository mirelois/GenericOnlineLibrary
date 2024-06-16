package com.aa.coolreads.User.models;

import com.aa.coolreads.Book.models.Book;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_username", referencedColumnName = "username")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="customer_creator_username", referencedColumnName = "username")
    private Customer customerCreator;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne
    private Book relatedBook;

    public Notification() {}

    public Notification(Customer customer, Customer customerCreator, NotificationType notificationType) {
        this.customer = customer;
        this.customerCreator = customerCreator;
        this.notificationType = notificationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Customer getCustomerCreator() {
        return customerCreator;
    }

    public void setCustomerCreator(Customer customerCreator) {
        this.customerCreator = customerCreator;
    }
}
