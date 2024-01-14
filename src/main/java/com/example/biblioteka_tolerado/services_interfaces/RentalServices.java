package com.example.biblioteka_tolerado.services_interfaces;

import com.example.biblioteka_tolerado.classes.Book;
import com.example.biblioteka_tolerado.classes.Rental;
import com.example.biblioteka_tolerado.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalServices {
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository; // Zakładam, że masz BookRepository do zarządzania książkami

    @Autowired
    public RentalServices(RentalRepository rentalRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
    }

    // Nowe wypożyczenie
    public Rental rentBook(User user, Book book) {
        Rental newRent = new Rental(user, book);
        book.decreaseAvailability();
        return rentalRepository.save(newRent);
    }

    // Zwracanie książek, aktualizacja statusu
    public void returnBook(User user, Long loanID, Book book) {
        Rental rental = rentalRepository.findByUserAndLoanID(user, loanID);

        if (rental != null && !rental.isReturned()) {
            book.increaseAvailability();
            rental.setReturned(true);
            rentalRepository.save(rental);
            bookRepository.save(book);
        } else {
            // Obsługa sytuacji, gdy wypożyczenie nie istnieje lub zostało już zwrócone
            throw new RuntimeException("Wypożyczenie nie znalezione lub już zwrócone");
        }
    }

    // Pokaż wypożyczenia dla danego użytkownika
    public Iterable<Rental> showRentalsByUserID(User user) {
        return rentalRepository.findByUser(user);
    }

    // Pokaż wypożyczenia dla danej książki
    public Iterable<Rental> showRentalsByBookID(Book book) {
        return rentalRepository.findByBook(book);
    }

    public Iterable<Rental> getRentalsForBook(Book book) {
        return rentalRepository.findByBook(book);
    }

    public Iterable<Rental> getRentalsForUser(User user) {
        return rentalRepository.findByUser(user);
    }
}
