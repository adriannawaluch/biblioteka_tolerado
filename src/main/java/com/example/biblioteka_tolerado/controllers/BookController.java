package com.example.biblioteka_tolerado.controllers;
import com.example.biblioteka_tolerado.classes.Books;
import com.example.biblioteka_tolerado.services_interfaces.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="/api/books", method = RequestMethod.GET)
public class BookController {
    private BookServices bookService;

    @Autowired
    public BookController(BookServices bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public Books addBook(@RequestParam String title, @RequestParam String language, @RequestParam Integer availability) {
        return bookService.addBook(title, language, availability);
    }

    @GetMapping("/all")
    public List<Books> findAllBooks() {
        return bookService.findAllBooks();
    }
    @GetMapping("/by_id")
    public Books findBooksById(@RequestParam Integer bookId) {
        return bookService.findBooksByBookId(bookId);
    }

    @DeleteMapping("/deleteBook")
    public void deleteBook(@RequestParam int bookId) {
        bookService.deleteBook(bookId);
    }
}