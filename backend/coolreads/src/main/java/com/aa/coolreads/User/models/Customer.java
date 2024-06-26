package com.aa.coolreads.User.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("customer")
public class Customer implements UserDetails {
    @Id
    private String username;

    private String password;

    private String email;

    @Embedded
    private CustomerProfileDetails profileDetails;

    @ManyToMany
    private Set<Customer> friends;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bookshelf> bookshelves;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonalBook> personalBooks;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExclusivityClass> exclusivityClasses;

    public Customer(){
        this.bookshelves = new HashSet<>();
        this.notifications = new HashSet<>();
        this.posts = new HashSet<>();
        this.friends = new HashSet<>();
        this.profileDetails = new CustomerProfileDetails();
        this.personalBooks = new HashSet<>();
        this.exclusivityClasses = new HashSet<>();
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<Bookshelf> bookshelves) {
        this.bookshelves = bookshelves;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public CustomerProfileDetails getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(CustomerProfileDetails profileDetails) {
        this.profileDetails = profileDetails;
    }

    public Set<Customer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Customer> friends) {
        this.friends = friends;
    }

    public void addFriend(Customer customer){
        this.friends.add(customer);
    }

    public void removeFriend(Customer customer){
        this.friends.remove(customer);
    }

    public void addNotification(Notification notification){
        this.notifications.add(notification);
    }

    public void addBookshelf(Bookshelf bookshelf){
        this.bookshelves.add(bookshelf);
    }

    public Set<PersonalBook> getPersonalBooks() {
        return personalBooks;
    }

    public void setPersonalBooks(Set<PersonalBook> personalBooks) {
        this.personalBooks = personalBooks;
    }

    public Set<ExclusivityClass> getExclusivityClasses() {
        return exclusivityClasses;
    }

    public void setExclusivityClasses(Set<ExclusivityClass> exclusivityClasses) {
        this.exclusivityClasses = exclusivityClasses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profileDetails=" + profileDetails +
                ", friends=" + friends +
                ", bookshelves=" + bookshelves +
                ", personalBooks=" + personalBooks +
                ", notifications=" + notifications +
                ", posts=" + posts +
                ", exclusivityClasses=" + exclusivityClasses +
                '}';
    }
}
