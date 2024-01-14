package com.example.biblioteka_tolerado.controllers;
import com.example.biblioteka_tolerado.classes.User;
import com.example.biblioteka_tolerado.services_interfaces.PasswordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordServices passwordService;

    @PostMapping("/save")
    public void savePassword(@RequestParam User user, @RequestParam String password) {
        passwordService.savePassword(user, password);
    }

    @GetMapping("/get")
    public String getPassword(@RequestParam Long userID) {
        return passwordService.getPasswordByUserID(userID);
    }
}
