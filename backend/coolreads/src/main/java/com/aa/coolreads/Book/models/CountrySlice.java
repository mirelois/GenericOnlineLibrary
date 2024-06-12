package com.aa.coolreads.Book.models;

public class CountrySlice implements Slice {

    private String country;

    private Integer amount;

    public CountrySlice(String country, Integer amount) {
        this.country = country;
        this.amount = amount;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String getCategoryName(){
        return this.country;
    }
}
