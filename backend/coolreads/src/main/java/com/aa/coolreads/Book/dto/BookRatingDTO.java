package com.aa.coolreads.Book.dto;

public class BookRatingDTO {
    private double rating;

    private String CustomerUserName;

    private BookRatingDTO(){}

    public BookRatingDTO(double rating, String CustomerUserName) {
        this.rating = rating;
        this.CustomerUserName = CustomerUserName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCustomerUserName() {
        return CustomerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        CustomerUserName = customerUserName;
    }
}
