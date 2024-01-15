package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Rental;
import com.example.biblioteka_tolerado.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    Rental findByUserAndLoanID(User user, Long loanID);

    Rental findByUser_UserId(Long userId);

    Rental findByBooks_BookId(int bookId);
}
