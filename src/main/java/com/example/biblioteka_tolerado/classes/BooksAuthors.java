package com.example.biblioteka_tolerado.classes;

import jakarta.persistence.*;

import java.awt.print.Book;

@Entity
@Table(name = "books_authors")
public class BooksAuthors {
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;
    @Id
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public BooksAuthors(Books book, Author author){
        this.book = book;
        this.author = author;
    }

    public BooksAuthors() {
    }
}
