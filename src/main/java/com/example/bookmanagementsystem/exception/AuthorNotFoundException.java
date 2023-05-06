package com.example.bookmanagementsystem.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String id) {
        super("Author not found with id: " + id);
    }
}
