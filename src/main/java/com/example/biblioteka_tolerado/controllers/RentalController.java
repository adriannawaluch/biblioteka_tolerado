//package com.example.biblioteka_tolerado.controllers;
//import com.example.biblioteka_tolerado.classes.Book;
//import com.example.biblioteka_tolerado.classes.Rental;
//import com.example.biblioteka_tolerado.classes.User;
//import com.example.biblioteka_tolerado.services_interfaces.RentalServices;
//import com.example.biblioteka_tolerado.services_interfaces.BookServices;
//import com.example.biblioteka_tolerado.services_interfaces.UserServices;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
////@RestController
////@RequestMapping("/rental")
//public class RentalController {
////    private final RentalServices rentalService;
////    private final UserServices userService;
////    private final BookServices bookService;
////
////    @Autowired
////    public RentalController(RentalServices rentalService, UserServices userService, BookServices bookService) {
////        this.rentalService = rentalService;
////        this.userService = userService;
////        this.bookService = bookService;
////    }
////
////    // Endpoint do wypożyczania książki
////    @PostMapping("/rent")
////    public ResponseEntity<Rental> rentBook(@RequestParam Long userId, @RequestParam Long bookId) {
////        User user = userService.findUserById(userId);
////        Book book = bookService.findBookById(bookId);
////
////        if (user != null && book != null) {
////            Rental rentedBook = rentalService.rentBook(user, book);
////            return new ResponseEntity<>(rentedBook, HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
////
////    // Endpoint do zwracania książki
////    @PostMapping("/return")
////    public ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Long loanId, @RequestParam Long bookId) {
////        User user = userService.findUserById(userId);
////        Book book = bookService.findBookById(bookId);
////
////        if (user != null && book != null) {
////            rentalService.returnBook(user, loanId, book);
////            return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>("User or book not found", HttpStatus.NOT_FOUND);
////        }
////    }
////
////    // Endpoint do pobierania wypożyczeń dla konkretnej książki
////    @GetMapping("/book/{bookId}")
////    public ResponseEntity<Iterable<Rental>> getRentalsForBook(@PathVariable Long bookId) {
////        Book book = bookService.findBookById(bookId);
////
////        if (book != null) {
////            Iterable<Rental> rentals = rentalService.getRentalsForBook(book);
////            return new ResponseEntity<>(rentals, HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
////
////    // Endpoint do pobierania wypożyczeń dla konkretnego użytkownika
////    @GetMapping("/user/{userId}")
////    public ResponseEntity<Iterable<Rental>> getRentalsForUser(@PathVariable Long userId) {
////        User user = userService.findUserById(userId);
////
////        if (user != null) {
////            Iterable<Rental> rentals = rentalService.getRentalsForUser(user);
////            return new ResponseEntity<>(rentals, HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
//}
