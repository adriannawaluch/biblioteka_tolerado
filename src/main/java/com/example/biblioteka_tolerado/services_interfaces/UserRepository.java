package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserLogin(String userLogin);
}