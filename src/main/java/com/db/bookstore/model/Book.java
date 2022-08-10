package com.db.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToMany
    private Set<Author> authorList;
    private int pages;
    private String publisher;

    public Book(String title, int pages, String publisher) {
        this.title = title;
        this.pages = pages;
        this.publisher = publisher;
    }

    public void setAuthorList(Set<Author> authorList) {
        this.authorList = authorList;
    }
}
