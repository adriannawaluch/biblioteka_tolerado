package com.example.biblioteka_tolerado.classes;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Authors")
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorID", unique = true)
    private int authorId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "authors")
    private Set<Books> books = new HashSet<>();

    //Kontruktory gettery i settery
    public Author(){}
    public Author(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public void setAuthorId(int id) {
        this.authorId = id;
    }
    public int getAuthorId() {
        return authorId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String name) {
        this.firstName = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
    public Set<Books> getBooks() {return books;}
    public void setBooks(Set<Books> books) { this.books = books;}

    public void addBook(Books books) {
        this.books.add(books);
        books.getAuthors().add(this);
    }

    public void removeBook(Books books) {
        this.books.remove(books);
        books.getAuthors().remove(this);
    }

}

