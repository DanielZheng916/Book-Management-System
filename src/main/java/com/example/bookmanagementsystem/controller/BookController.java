package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.exception.AuthorNotFoundException;
import com.example.bookmanagementsystem.exception.BookNotFoundException;
import com.example.bookmanagementsystem.model.Author;
import com.example.bookmanagementsystem.model.Book;
import com.example.bookmanagementsystem.model.BookAuthor;
import com.example.bookmanagementsystem.repository.AuthorRepository;
import com.example.bookmanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepository repository;
    private AuthorRepository authorRepository;

    public BookController(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/")
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

//    @PostMapping("/")
//    public Book create(@RequestBody Book book) {
//        List<String> authorsID = book.getAuthorsID();
//        if (authorsID != null && authorsID.size() != 0) {
//            List<Author> newAuthors = new LinkedList<>();
//            for (String authorID : authorsID) {
//                newAuthors.add(authorRepository.save(author));
//            }
//            book.setAuthors(newAuthors);
//        }
//
//        return repository.save(book);
//    }

    @PutMapping("/")
    public Book addAuthor(@RequestBody BookAuthor bookAuthor) {
        String bookID = bookAuthor.getBookID();
        String authorID = bookAuthor.getAuthorID();
        Book book = repository.findById(bookID)
                .orElseThrow(() -> new BookNotFoundException(bookID));
        Author author = authorRepository.findById(authorID)
                .orElseThrow(() -> new AuthorNotFoundException(authorID));
        book.getAuthorsID().add(authorID);
        author.getBooksID().add(bookID);
        authorRepository.save(author);

        return repository.save(book);
    }

}
