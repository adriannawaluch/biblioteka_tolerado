//package com.example.biblioteka_tolerado.classes;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "Users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "userID")
//    private Long id;
//
//    @Column(name = "first_name")
//    private String first_name;
//
//    @Column(name = "last_name")
//    private String last_name;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "user_login")
//    private String userLogin;
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private Password password;
//
//    public User() {
//    }
//
//    public User(String userLogin, String firstName, String lastName, String email) {
//        this.userLogin = userLogin;
//        this.first_name = firstName;
//        this.last_name = lastName;
//        this.email = email;
//    }
//
//    // Settery i gettery
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getUserLogin() {
//        return userLogin;
//    }
//
//    public void setUserLogin(String user_login) {
//        this.userLogin = user_login;
//    }
//
//    public String getFirst_name() {
//        return first_name;
//    }
//
//    public void setFirst_name(String first_name) {
//        this.first_name = first_name;
//    }
//
//    public String getLast_name() {
//        return last_name;
//    }
//
//    public void setLast_name(String last_name) {
//        this.last_name = last_name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Password getPassword() {
//        return password;
//    }
//
//    public void setPassword(Password password) {
//        if (password == null) {
//            if (this.password != null) {
//                this.password.setUser(null);
//            }
//        } else {
//            password.setUser(this);
//        }
//        this.password = password;
//    }
//}
