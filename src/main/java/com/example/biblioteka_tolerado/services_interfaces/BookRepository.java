package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Books;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Books, Integer> {
    List<Books> findAll();
    List<Books> findBooksByBookId(Integer bookId);
}
