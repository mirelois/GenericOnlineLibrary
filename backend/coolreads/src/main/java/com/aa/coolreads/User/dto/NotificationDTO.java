package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.NotificationType;

import java.util.Date;

public class NotificationDTO extends NotificationCreationDTO{

    private Long id;

    private String username;

    private String title;

    private String author;

    private Date createdAt;

    public NotificationDTO(NotificationType notificationType, Long id, String username, String title, String author, Date createdAt) {

        super(notificationType);

        this.id = id;
        this.username = username;
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
