package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServices {
    private final BookRepository bookRepository;
    @Autowired
    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book addBook(String title, String language, int availability ) {
        Book book = new Book();
        book.setTitle(title);
        book.setLanguage(language);
        book.setAvailability(availability);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findBookById(Long bookId) {
        return null;
    }
}
