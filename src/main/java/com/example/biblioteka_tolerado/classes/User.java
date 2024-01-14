package com.example.biblioteka_tolerado.classes;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_login")
    private String userLogin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Password password;

    public User() {
    }

    public User(String userLogin, String firstName, String lastName, String email) {
        this.userLogin = userLogin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Settery i gettery

    public Long getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String user_login) {
        this.userLogin = user_login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        if (password == null) {
            if (this.password != null) {
                this.password.setUser(null);
            }
        } else {
            password.setUser(this);
        }
        this.password = password;
    }
}
