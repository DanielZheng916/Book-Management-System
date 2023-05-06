package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.exception.AuthorNotFoundException;
import com.example.bookmanagementsystem.model.Author;
import com.example.bookmanagementsystem.model.AuthorWithBookInfo;
import com.example.bookmanagementsystem.repository.AuthorRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private KafkaTemplate<String, String> kafkaTemplate;

    private AuthorRepository repository;

    @Value(value = "${author.topic.name}")
    private String topicAuthor;

    @Autowired
    private MongoTemplate mongoTemplate;

    public AuthorController(AuthorRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/")
    public Author create(@RequestBody Author newAuthor) {
        Author createdAuthor = repository.save(newAuthor);
        String message = "Create author: " + createdAuthor;
        sendMessage(topicAuthor, message);
        return createdAuthor;
    }

    @GetMapping("/")
    List<Author> all() {
//        return repository.findAll();
//        return repository.findAllWithBookInfo();

        AggregationOperation lookupOperation = Aggregation.lookup("book", "booksID", "id", "book");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(new Criteria()), // matches all documents in the collection
                lookupOperation
        );
        AggregationResults<Author> results = mongoTemplate.aggregate(aggregation, "author", Author.class);
        List<Author> authorsWithBooks = results.getMappedResults();

        System.out.println("stop");

        return authorsWithBooks;
    }

    @GetMapping("/{id}")
    public Author one(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Author update(@RequestBody Author newAuthor, @PathVariable String id) {
        Author author = repository.findById(id).orElse(null);
        if (author == null) {
            author = newAuthor;
        } else {
            author.setFirstName(newAuthor.getFirstName());
            author.setLastName(newAuthor.getLastName());
        }
        return repository.save(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

    private void sendMessage(String topicName, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {

            if (ex == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
