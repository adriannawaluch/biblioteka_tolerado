package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Books;
import com.example.biblioteka_tolerado.classes.Rental;
import com.example.biblioteka_tolerado.classes.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalServices {
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository; // Zakładam, że masz BookRepository do zarządzania książkami
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RentalServices(RentalRepository rentalRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
    }

    // Nowe wypożyczenie
    @Transactional
    public Rental rentBook(User user, Books books) {
        if (!entityManager.contains(books)) {
            // Jeśli nie jest zarządzany, możesz go dołączyć do kontekstu JPA
            books = entityManager.merge(books);
        }

        LocalDate currentDate = LocalDate.now(); // Pobierz dzisiejszą datę

        Rental newRent = new Rental(user, books);
        newRent.setLoanDate(currentDate); // Ustaw datę wypożyczenia na dzisiejszą datę

        books.decreaseAvailability();
        System.out.println(books.getAvailability());

        return rentalRepository.save(newRent);
    }

    // Zwracanie książek, aktualizacja statusu
    public void returnBook(User user, Long loanID, Books books) {
        Rental rental = rentalRepository.findByUserAndLoanID(user, loanID);

        if (rental != null && !rental.isReturned()) {
            books.increaseAvailability();

            LocalDate todayDate = LocalDate.now(); // Pobierz dzisiejszą datę

            rental.setReturned(true);
            rental.setReturnDate(todayDate); // Ustaw datę zwrotu na dzisiejszą datę

            rentalRepository.save(rental);
            bookRepository.save(books);
            System.out.println("Książka została zwrócona.");
        } else {
            System.out.println("Wypożyczenie nie znalezione lub już zwrócone.");
        }
    }

    // Pokaż wypożyczenia dla danego użytkownika
    public List<Rental> showRentalsByUserID(Long userId) {
        return rentalRepository.findByUser_UserId(userId);
    }

    // Pokaż wypożyczenia dla danej książki
    public Rental showRentalsByBookID(int bookId) {
        return rentalRepository.findByBooks_BookId(bookId);
    }
}