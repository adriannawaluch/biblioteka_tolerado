//package com.example.biblioteka_tolerado.services_interfaces;
//import com.example.biblioteka_tolerado.classes.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUserLogin(String userLogin);
//
//    void deleteById(Long id);
//
//    Optional<User> findById(Long userId);
//
//    void save(User user);
//}