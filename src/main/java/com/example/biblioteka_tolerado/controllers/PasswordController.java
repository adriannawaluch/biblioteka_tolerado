package com.example.biblioteka_tolerado.controllers;
import com.example.biblioteka_tolerado.classes.User;
import com.example.biblioteka_tolerado.services_interfaces.PasswordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordController {

    private PasswordServices passwordService;
    @Autowired
    public PasswordController(PasswordServices passwordService){
        this.passwordService = passwordService;
    }

    @PostMapping("/save")
    public void savePassword(@RequestParam User user, @RequestParam String password) {
        passwordService.savePassword(user, password);
    }

    @GetMapping("/get")
    public String getPassword(@RequestParam Long userId) {
        return passwordService.getPasswordByUserID(userId);
    }

    @GetMapping("/change")
    public void changePassword(@RequestParam int userId, String oldPassword, String newPassword) {
        passwordService.updatePassword((long)userId, oldPassword, newPassword);
    }

}
