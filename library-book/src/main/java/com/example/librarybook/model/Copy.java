package com.example.librarybook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Copy {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    private int book;

    public Copy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", book=" + book +
                '}';
    }
}
