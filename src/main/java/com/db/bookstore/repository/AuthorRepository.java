package com.db.bookstore.repository;

import com.db.bookstore.model.Author;
import com.db.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAll();
    Author findByName(String name);
}
