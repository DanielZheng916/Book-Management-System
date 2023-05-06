package com.example.bookmanagementsystem.config;

import com.example.bookmanagementsystem.model.Author;
import com.example.bookmanagementsystem.repository.AuthorRepository;
import com.example.bookmanagementsystem.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AuthorRepository repository, BookRepository bookRepository) {

        bookRepository.deleteAll();
        repository.deleteAll();

        return args -> {
            log.info("Preloading " + repository.save(new Author("Bilbo", "Baggins")));
            log.info("Preloading " + repository.save(new Author("Frodo", "Baggins")));
        };
    }
}
