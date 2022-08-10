package com.db.bookstore.service;

import com.db.bookstore.model.Author;
import com.db.bookstore.model.Book;
import com.db.bookstore.repository.AuthorRepository;
import com.db.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;


    public List<Author> getAll() {
        return (List<Author>) authorRepository.findAll();
    }

    public Author getByName(String name) { return authorRepository.findByName(name);}
}

