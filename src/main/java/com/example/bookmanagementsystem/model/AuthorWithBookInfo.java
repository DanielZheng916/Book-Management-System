package com.example.bookmanagementsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorWithBookInfo {

    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String nationality;
    private String biography;
    private List<String> booksID;
    private List<Object> booksInfo;
}
