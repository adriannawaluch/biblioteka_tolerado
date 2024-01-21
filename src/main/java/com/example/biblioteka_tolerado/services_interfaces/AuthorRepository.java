package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByFirstNameAndLastName(String first_name, String last_name);
}