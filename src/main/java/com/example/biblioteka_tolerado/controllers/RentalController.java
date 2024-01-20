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

import java.util.List;

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
    public Rental rentBook(@RequestParam int bookId) {
        int userId = 1;
        User user = userService.findUserById((long)userId);
        Books books = bookService.findBooksByBookId(bookId);
        System.out.println(user);
        System.out.println(books);
        if (user != null && books != null) {
            System.out.println("udalo sie rent");
            return rentalService.rentBook(user, books);
        } else {
            System.out.println("nie udalo sie wypozyczy ksiazki");
            return null;
        }
    }

    // Endpoint do zwracania książki
    @PostMapping("/return")
    public void returnBook(@RequestParam(required = false) Integer userId, @RequestParam Long loanId, @RequestParam int bookId) {
        userId=1;
        User user = userService.findUserById((long)userId);
        Books books = bookService.findBooksByBookId(bookId);
        System.out.println(userId + bookId);

        if (user != null && books != null) {
            rentalService.returnBook(user, loanId, books);
        } else {
            System.out.println("nie udalo sie");
        }
    }

    // Endpoint do pobierania wypożyczeń dla konkretnego użytkownika
    @GetMapping("/user")
    @ResponseBody
    public List<Rental> showRentalsByUserID(@RequestParam(required = false) Integer userId) {
        userId =1;
        if (userId != 0) {
            System.out.println("udalo sie po user id");
            return rentalService.showRentalsByUserID((long)userId);
        } else {
            return null;
        }
    }
}
