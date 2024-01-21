package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Author;
import com.example.biblioteka_tolerado.services_interfaces.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthorServices {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServices(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findAuthorByFirstNameAndLastName(String first_name, String last_name) {
        return authorRepository.findAuthorByFirstNameAndLastName(first_name, last_name);
    }

}
