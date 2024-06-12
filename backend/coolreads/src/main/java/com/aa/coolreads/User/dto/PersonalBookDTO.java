package com.aa.coolreads.User.dto;


import java.util.Date;

public class PersonalBookDTO {

    private Integer pagesRead;

    private Date insertDate;

    private String bookISBN;

    private String coverImage;

    private String title;

    public PersonalBookDTO(){}

    public PersonalBookDTO(Integer pagesRead, Date insertDate, String bookISBN, String coverImage, String title) {
        this.pagesRead = pagesRead;
        this.insertDate = insertDate;
        this.bookISBN = bookISBN;
        this.coverImage = coverImage;
        this.title = title;
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

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

}
