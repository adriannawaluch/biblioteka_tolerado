package com.example.biblioteka_tolerado.classes;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorID")
    private int id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastname;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "BooksAuthors",
            joinColumns = @JoinColumn(name = "authorID"),
            inverseJoinColumns = @JoinColumn(name = "bookID")
    )
    private Set<Book> books = new HashSet<>();

    //Kontruktory gettery i settery
    public Author(){}
    public Author(int id, String name, String lastname){
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Set<Book> getBooks() {return books;}
    public void setBooks(Set<Book> books) { this.books = books;}

    public void addBook(Book book) {
        books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getAuthors().remove(this);
    }

}

