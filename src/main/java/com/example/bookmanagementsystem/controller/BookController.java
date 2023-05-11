package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.exception.AuthorNotFoundException;
import com.example.bookmanagementsystem.exception.BookNotFoundException;
import com.example.bookmanagementsystem.model.Author;
import com.example.bookmanagementsystem.model.Book;
import com.example.bookmanagementsystem.model.BookAuthor;
import com.example.bookmanagementsystem.repository.AuthorRepository;
import com.example.bookmanagementsystem.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Tag(name = "Book", description = "The book API")
@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepository repository;
    private AuthorRepository authorRepository;

    public BookController(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    @Operation(summary = "Create new book", description = "Creates new book description", tags = { "Book" })
    @PostMapping("/")
    public Book create(@RequestBody Book book) {
        List<Author> authors = book.getAuthors();
        if (authors != null) {
            List<Author> newAuthors = new LinkedList<>();
            for (Author author : authors) {
                newAuthors.add(authorRepository.save(author));
            }
            book.setAuthors(newAuthors);
        }

        return repository.save(book);
    }

    @PutMapping("/")
    public Book addAuthor(@RequestBody BookAuthor bookAuthor) {
        Long bookID = bookAuthor.getBookID();
        Long authorID = bookAuthor.getAuthorID();
        Book book = repository.findById(bookID)
                .orElseThrow(() -> new BookNotFoundException(bookID));
        Author author = authorRepository.findById(authorID)
                .orElseThrow(() -> new AuthorNotFoundException(authorID));
        book.getAuthors().add(author);

        return repository.save(book);
    }

}
