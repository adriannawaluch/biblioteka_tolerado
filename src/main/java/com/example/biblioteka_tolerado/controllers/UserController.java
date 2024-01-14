//package com.example.biblioteka_tolerado.controllers;
//import com.example.biblioteka_tolerado.classes.Password;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import com.example.biblioteka_tolerado.classes.User;
//import com.example.biblioteka_tolerado.services_interfaces.UserServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value="/users", method = RequestMethod.GET)
//public class UserController {
//    @Autowired
//    private UserServices userServices;
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createUser(
//            @RequestParam String user_login,
//            @RequestParam String firstName,
//            @RequestParam String lastName,
//            @RequestParam String email,
//            @RequestParam String password) {
//
//        User user = new User(user_login, firstName, lastName, email);
//        Password passwordEntity = new Password(user, password);
//        passwordEntity.setUser(user);
//
//        userServices.createUser(user.getUserLogin(),user.getFirst_name(),user.getLast_name(), user.getEmail(), passwordEntity.getPassword());
//
//        return ResponseEntity.ok("User created successfully.");
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String userLogin, @RequestParam String password, Model model) {
//        boolean loginSuccessful = userServices.login(userLogin, password);
//
//        if (loginSuccessful) {
//            System.out.println("ZALOGOWANY");
//            return "zalogowany";
//        } else {
//            // If login fails, display an error message
//            model.addAttribute("error", "Invalid username or password");
//            return "login again"; // Return to the login page with an error message
//        }
//    }
//
//    @PostMapping("/delete")
//    public void deleteUser(@RequestParam String userLogin, @RequestParam String password) {
////    public User createUser(@RequestBody User user) {
////        return userServices.createUser(user.getUserLogin(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword().getPassword());
//        userServices.delete(userLogin, password);
//    }
//}
//
