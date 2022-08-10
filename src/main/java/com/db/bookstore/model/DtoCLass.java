package com.db.bookstore.model;

import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

public class DtoCLass {
    private String title;
    private List<String> authorNames;
    private int pages;
    private String publisher;

    public DtoCLass() {
    }

    public DtoCLass(String title, List<String> authorNames, int pages, String publisher) {
        this.title = title;
        this.authorNames = authorNames;
        this.pages = pages;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
