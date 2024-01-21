package com.example.biblioteka_tolerado.classes;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Books")
@Table(name = "Books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id", unique = true)
    private int bookId;

    @Column(name="title")
    private String title;

    @Column(name="language")
    private String language;
    @Column(name="availability")
    private int availability;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "BooksAuthors",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId"),
            uniqueConstraints = @UniqueConstraint(name = "UC_BooksAuthors_Book", columnNames = {"bookId", "authorId"})
    )
    private Set<Author> authors = new HashSet<>();


    // Konstruktory, gettery i setter
    public Books() {
    }
    public Books(String title, String language) {
        this.title = title;
        this.language = language;
    }
    public Books(String title, String language, Set<Author> authors) {
        this.title = title;
        this.language = language;
        this.authors = authors;
    }
    public Books(String title, String language, Set<Author> authors, int availability) {
        this.title = title;
        this.language = language;
        this.authors = authors;
        this.availability = availability;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int id) {
        this.bookId = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLanguage(){
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getAvailability() {
        return availability;
    }
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    //inne funkcje
    public void increaseAvailability() {
        this.availability++;
    }

    public void decreaseAvailability() { this.availability = this.availability-1; }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }
    @Override
    public String toString() {
        return "Book{" +
                "id='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + authors + '\'' +
                // Include other fields as needed
                '}';
    }


}
