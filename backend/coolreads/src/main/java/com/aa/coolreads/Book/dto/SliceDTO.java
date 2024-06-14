package com.aa.coolreads.Book.dto;

public class SliceDTO {

    private String categoryName;

    private Integer amount;

    public SliceDTO(String categoryName, Integer percentage) {
        this.categoryName = categoryName;
        this.amount = percentage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
