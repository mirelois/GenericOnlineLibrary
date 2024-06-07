package com.aa.coolreads.User.dto;


import java.util.Date;

public class PersonalBookDTO {

    private Integer pagesRead;

    private Date insertDate;

    private String bookISBN;

    public PersonalBookDTO(){}

    public PersonalBookDTO(Integer pagesRead, Date insertDate, String bookISBN) {
        this.pagesRead = pagesRead;
        this.insertDate = insertDate;
        this.bookISBN = bookISBN;
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
}
