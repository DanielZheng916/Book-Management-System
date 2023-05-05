package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
