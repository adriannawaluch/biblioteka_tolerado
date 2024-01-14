package com.example.biblioteka_tolerado.services_interfaces;
import com.example.biblioteka_tolerado.classes.Password;
import com.example.biblioteka_tolerado.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public void updatePassword(Long userId, String newPassword) {
        Password password = passwordRepository.findById(userId).orElse(null);
        if (password != null) {
            password.setPassword(newPassword);
            passwordRepository.save(password);
        } else {
            // Obsłuż sytuację, gdy nie ma hasła dla danego użytkownika
            // Możesz rzucić wyjątek lub zaimplementować odpowiednią logikę obsługi
        }
    }
//czy to w ogole ma sens
    public String getPasswordByUserID(Long userID) {
        Password password = passwordRepository.findById(userID).orElse(null);
        return (password != null) ? password.getPassword() : null;
    }
}