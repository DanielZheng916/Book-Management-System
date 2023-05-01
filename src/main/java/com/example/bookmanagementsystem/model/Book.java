package com.example.bookmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Book {

    private @Id
    @GeneratedValue Long id;
    private String title;

    @ManyToMany(targetEntity = Author.class)
    private List<Author> authors;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", authors=").append(authors);
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
