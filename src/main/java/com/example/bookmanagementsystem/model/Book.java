package com.example.bookmanagementsystem.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document
public class Book {

    @Id
    private String id;
    private String title;

    private List<String> authorsID = new ArrayList<>();
    private String ISBN;
    private String publisher;
    private LocalDate publicationDate;
    private String edition;
    private String language;
    private String genre;
    private int pages;
    private int availableCopies;
    private String description;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthorsID() {
        return authorsID;
    }

    public void setAuthorsID(List<String> authorsID) {
        this.authorsID = authorsID;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", authorsID=").append(authorsID);
        sb.append(", ISBN='").append(ISBN).append('\'');
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", edition='").append(edition).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", pages=").append(pages);
        sb.append(", availableCopies=").append(availableCopies);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
