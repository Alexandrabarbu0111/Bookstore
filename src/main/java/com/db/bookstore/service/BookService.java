package com.db.bookstore.service;

import com.db.bookstore.model.Book;
import com.db.bookstore.repository.BookRepository;
import com.db.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;


    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Transactional
    public Book add(Book book) {
        Book newBook = bookRepository.save(book);
        return newBook;
    }

}
