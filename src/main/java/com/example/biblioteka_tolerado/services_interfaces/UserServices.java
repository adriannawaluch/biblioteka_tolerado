package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Password;
import com.example.biblioteka_tolerado.classes.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    private UserRepository userRepository;
    @Autowired
    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //nowy uzytkownik
    @Transactional
    public User createUser(String userLogin, String firstName, String lastName, String email, String passwordVal) {
        // Tworzenie użytkownika
        User user = new User();
        user.setUserLogin(userLogin);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        Password password = new Password();
        password.setPassword(passwordVal);
        password.setUser(user);

        user.setPassword(password);

        // Zapis do bazy danych
        return userRepository.save(user);
    }
    //uwierzytelnianie
    public boolean login(String givenUserName, String givenPassword){
        User user = userRepository.findByUserLogin(givenUserName);
        Password password = new Password();
        password.setUser(user);
        password.setPassword(givenPassword);
        return user != null && user.getPassword().getPassword().equals(password.getPassword());
    }
    //usuwanie uzytkownika
    @Transactional
    public void delete(String userLogin, String password) {
        User userByLogin = userRepository.findByUserLogin(userLogin);

        // Sprawdzanie, czy hasło jest poprawne przed usunięciem użytkownika
        if (userByLogin.getPassword() != null && userByLogin.getPassword().getPassword().equals(password)) {
            userRepository.deleteById(userByLogin.getUserId());
        }
    }
    public User findUserById(Long userId) {
        return userRepository.findUserByUserId(userId);
    }
}
