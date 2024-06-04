package com.aa.coolreads.User.dto;


import java.util.Date;

public class PersonalBookDTO {

    private Integer pagesRead;

    private Date insertDate;

    private String bookshelfName;

    private String bookISBN;

    public PersonalBookDTO(){}

    public PersonalBookDTO(Integer pagesRead, Date insertDate, String bookshelfName, String bookISBN) {
        this.pagesRead = pagesRead;
        this.insertDate = insertDate;
        this.bookshelfName = bookshelfName;
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

    public String getBookshelfName() {
        return bookshelfName;
    }

    public void setBookshelfName(String bookshelfName) {
        this.bookshelfName = bookshelfName;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }
}
