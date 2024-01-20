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
        Rental newRent = new Rental(user, books);
        books.decreaseAvailability();
        System.out.println(books.getAvailability());
        return rentalRepository.save(newRent);
    }

    // Zwracanie książek, aktualizacja statusu
    public void returnBook(User user, Long loanID, Books books) {
//        Rental rental = rentalRepository.findByUserAndLoanID(user, loanID);
        System.out.println("wzrociles ksiazke");
//        if (rental != null && !rental.isReturned()) {
//            books.increaseAvailability();
//            rental.setReturned(true);
//            rentalRepository.save(rental);
//            bookRepository.save(books);
//        } else {
//            // Obsługa sytuacji, gdy wypożyczenie nie istnieje lub zostało już zwrócone
//            throw new RuntimeException("Wypożyczenie nie znalezione lub już zwrócone");
//        }
    }

    // Pokaż wypożyczenia dla danego użytkownika
    public Rental showRentalsByUserID(Long userId) {
        return rentalRepository.findByUser_UserId(userId);
    }

    // Pokaż wypożyczenia dla danej książki
    public Rental showRentalsByBookID(int bookId) {
        return rentalRepository.findByBooks_BookId(bookId);
    }
}