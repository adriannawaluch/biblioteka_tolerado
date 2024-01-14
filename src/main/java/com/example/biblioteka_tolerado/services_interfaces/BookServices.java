package com.example.biblioteka_tolerado.services_interfaces;
import org.springframework.lang.NonNull;
import com.example.biblioteka_tolerado.classes.Books;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServices {
    private BookRepository bookRepository;
    @Autowired
    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Transactional
    public Books addBook(String title, String language, int availability ) {
        if (title == null || title.isEmpty() || language == null || language.isEmpty() || availability < 0) {
            throw new IllegalArgumentException("Invalid input data for adding a book");
        }
        Books books = new Books();
        books.setTitle(title);
        books.setLanguage(language);
        books.setAvailability(availability);
        return bookRepository.save(books);
    }
    @Transactional
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
    @NonNull
    public List<Books> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Books> findBooksByBookId(int id){
        return bookRepository.findBooksByBookId(id);
    }
}
