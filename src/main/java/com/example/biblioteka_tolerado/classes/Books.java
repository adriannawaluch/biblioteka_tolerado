package com.example.biblioteka_tolerado.classes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Books")
@Table(name = "Books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true)
    private int bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "availability")
    private int availability;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            uniqueConstraints = @UniqueConstraint(name = "UC_BooksAuthors_Book", columnNames = {"book_id", "author_id"})
    )
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();

    // Konstruktory, gettery i settery
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

    public String getLanguage() {
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

    public void increaseAvailability() {
        this.availability++;
    }

    public void decreaseAvailability() {
        this.availability = this.availability - 1;
    }

    public Set<Author> getAuthors() {
        return authors;
    }
    public List<String> getAuthorsList() {
        List<String> authorNames = new ArrayList<>();
        for (Author author : authors) {
            authorNames.add(author.getFirstName() + " " + author.getLastName());
        }
        return authorNames;
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
        return "Books{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                // Include other fields as needed
                '}';
    }
}
