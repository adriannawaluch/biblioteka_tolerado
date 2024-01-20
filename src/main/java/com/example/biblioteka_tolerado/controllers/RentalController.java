package com.example.biblioteka_tolerado.controllers;
import com.example.biblioteka_tolerado.classes.Books;
import com.example.biblioteka_tolerado.classes.Rental;
import com.example.biblioteka_tolerado.classes.User;
import com.example.biblioteka_tolerado.services_interfaces.RentalServices;
import com.example.biblioteka_tolerado.services_interfaces.BookServices;
import com.example.biblioteka_tolerado.services_interfaces.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalServices rentalService;
    private final UserServices userService;
    private final BookServices bookService;

    @Autowired
    public RentalController(RentalServices rentalService, UserServices userService, BookServices bookService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.bookService = bookService;
    }

    // Endpoint do wypożyczania książki
    @PostMapping("/rent")
    public ResponseEntity<Rental> rentBook(@RequestParam int bookId) {
        int userId = 1;
        User user = userService.findUserById((long)userId);
        Books books = bookService.findBooksByBookId(bookId);
        System.out.println(user);
        System.out.println(books);
        if (user != null && books != null) {
            Rental rentedBook = rentalService.rentBook(user, books);
            System.out.println("udalo sie rent");
            return new ResponseEntity<>(rentedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint do zwracania książki
    @GetMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Long loanId, @RequestParam int bookId) {
        User user = userService.findUserById(userId);
        Books books = bookService.findBooksByBookId(bookId);
        System.out.println(userId + bookId);
        if (user != null && books != null) {
            rentalService.returnBook(user, loanId, books);
            return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User or books not found", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint do pobierania wypożyczeń dla konkretnej książki
    @GetMapping("/book/{bookId}")
    public ResponseEntity<Rental> showRentalsByBookID(@PathVariable int bookId) {
        if (bookId != 0) {
            Rental rentals = rentalService.showRentalsByBookID(bookId);
            System.out.println("udalo sie po book id");
            return new ResponseEntity<>(rentals, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint do pobierania wypożyczeń dla konkretnego użytkownika
    @GetMapping("/user/{userId}")
    public ResponseEntity<Rental> showRentalsByUserID(@PathVariable int userId) {
        if (userId != 0) {
            Rental rentals = rentalService.showRentalsByUserID((long)userId);
            System.out.println("udalo sie po user id");
            return new ResponseEntity<>(rentals, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
