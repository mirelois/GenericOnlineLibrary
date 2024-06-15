package com.aa.coolreads.Book.events;

public class ReviewMessage {

    private String username;

    private String bookISBN;

    public ReviewMessage(String username, String bookISBN) {
        this.username = username;
        this.bookISBN = bookISBN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }
}
