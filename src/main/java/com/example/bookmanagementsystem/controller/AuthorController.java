package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.exception.AuthorNotFoundException;
import com.example.bookmanagementsystem.model.Author;
import com.example.bookmanagementsystem.repository.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    public Author create(@RequestBody Author newAuthor) {
        return repository.save(newAuthor);
    }

    @GetMapping("/")
    List<Author> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Author one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Author update(@RequestBody Author newAuthor, @PathVariable Long id) {
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
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
