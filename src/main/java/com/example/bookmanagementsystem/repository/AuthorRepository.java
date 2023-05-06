package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.model.Author;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    @Aggregation(pipeline = {
            "{$lookup: { from: \"book\", localField: \"booksID\", foreignField: \"id\", as: \"bookDetails\"}}"
    })
    List<Author> findAllWithBookInfo();
}
