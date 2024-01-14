package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
