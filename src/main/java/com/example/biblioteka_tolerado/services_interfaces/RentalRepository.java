package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Book;
import com.example.biblioteka_tolerado.classes.Rental;
import com.example.biblioteka_tolerado.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    Rental findByUserAndLoanID(User user, Long loanID);

    Iterable<Rental> findByUser(User user);

    Iterable<Rental> findByBook(Book book);
}
