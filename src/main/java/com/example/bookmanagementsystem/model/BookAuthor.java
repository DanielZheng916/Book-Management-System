package com.example.bookmanagementsystem.model;

public class BookAuthor {
    private Long bookID;
    private Long authorID;

    public BookAuthor() {
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }
}
