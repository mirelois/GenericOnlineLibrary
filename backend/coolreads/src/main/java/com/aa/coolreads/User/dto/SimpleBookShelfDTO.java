package com.aa.coolreads.User.dto;

public class SimpleBookShelfDTO extends BookShelfCreationDTO{
    private Integer bookCount;

    public SimpleBookShelfDTO(){}

    public SimpleBookShelfDTO(String name, String privacy, Integer bookCount) {
        super(name, privacy);
        this.bookCount = bookCount;
    }


    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }
}
