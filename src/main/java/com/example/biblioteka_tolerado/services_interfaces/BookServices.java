package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Author;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.lang.NonNull;
import com.example.biblioteka_tolerado.classes.Books;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServices {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    @Autowired
    public BookServices(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @Transactional
    public Books addBook(String title, String language, String first_name, String last_name) {
        if (title == null || title.isEmpty() || language == null || language.isEmpty()) {
            throw new IllegalArgumentException("Invalid input data for adding a book");
        }

        Author author = authorRepository.findAuthorByFirstNameAndLastName(first_name, last_name);
        if (author == null) {
            author = new Author(first_name, last_name);
            authorRepository.save(author);
        }

        Books existingBook = bookRepository.findBooksByTitle(title);

        if (existingBook != null) {
            existingBook.increaseAvailability();
            return bookRepository.save(existingBook);
        } else {
            Books newBook = new Books(title, language);
            newBook.setAvailability(1);
            newBook.addAuthor(author);

            // Zapisz książkę, aby wygenerować identyfikator
            newBook = bookRepository.save(newBook);

            // Zaktualizuj autora, aby uwzględnić nowo utworzoną książkę
            author.addBook(newBook);
            authorRepository.save(author);
            System.out.println(author.getAuthorId());
            System.out.println(newBook.getBookId());
            return newBook;
        }
    }

    @Transactional
    public void deleteBook(int bookId) {
        Books bookToDelete = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));

        // Usuń związane rekordy z tabeli BooksAuthors
        removeBooksAuthorsRelationships(bookToDelete);

        // Usuń książkę
        bookRepository.delete(bookToDelete);
    }

    private void removeBooksAuthorsRelationships(Books book) {
        // Zbierz wszystkich autorów z książki
        Set<Author> authors = new HashSet<>(book.getAuthors());

        // Usuń związki z książką dla każdego autora
        for (Author author : authors) {
            author.removeBook(book);
            authorRepository.save(author);  // Zapisz autora, aby zaktualizować związki w BooksAuthors
        }
    }
    public List<Books> findAllBooks() {
        return bookRepository.findAll();
    }

    public Books findBooksByBookId(int id){
        return bookRepository.findBooksByBookId(id);
    }

    public Books findBooksByTitle(String title) {return bookRepository.findBooksByTitle(title);}

}
