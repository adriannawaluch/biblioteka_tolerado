package com.example.biblioteka_tolerado.classes;

import jakarta.persistence.*;

import java.awt.print.Book;

@Entity
@Table(name = "BooksAuthors")
public class BooksAuthors {
    @Id
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Books book;
    @Id
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    public BooksAuthors(Books book, Author author){
        this.book = book;
        this.author = author;
    }

    public BooksAuthors() {
    }
}
