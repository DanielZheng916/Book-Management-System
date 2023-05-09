package com.example.bookmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
