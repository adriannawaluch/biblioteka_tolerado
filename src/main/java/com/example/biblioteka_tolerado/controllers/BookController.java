package com.example.biblioteka_tolerado.controllers;
import com.example.biblioteka_tolerado.classes.Book;
import com.example.biblioteka_tolerado.services_interfaces.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="/api/books", method = RequestMethod.GET)
public class BookController {
    private final BookServices bookService;
    @Autowired
    public BookController(BookServices bookService) {
        this.bookService = bookService;
    }

//    @PostMapping("/addBook")
//    public Book addBook(@RequestParam String title, @RequestParam String language, @RequestParam Integer availability) {
//        return bookService.addBook(title, language, availability);
//    }
//
//    @GetMapping("/all")
//    public List<Book> getAllBooks() {
//        return bookService.findAllBooks();
//    }
//
//    @DeleteMapping("/deleteBook")
//    public void deleteBook(@RequestParam int bookId) {
//        bookService.deleteBook((long) bookId);
//    }
}