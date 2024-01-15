package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Password;
import com.example.biblioteka_tolerado.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class PasswordServices {

    private PasswordRepository passwordRepository;
    @Autowired
    public PasswordServices(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }
    public void savePassword(User user, String password) {
        // haszowanie, sprawdzanie
        Password newPassword = new Password(user, password);
        passwordRepository.save(newPassword);
    }

    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        Password password = passwordRepository.findById(userId).orElse(null);
        if (password != null && Objects.equals(password.getPassword(), oldPassword)) {
            password.setPassword(newPassword);
            passwordRepository.save(password);
        } else {
            System.out.println("niepoprawne stare haslo");
        }
    }
//czy to w ogole ma sens
    public String getPasswordByUserID(Long userID) {
        Password password = passwordRepository.findById(userID).orElse(null);
        return (password != null) ? password.getPassword() : null;
    }
}