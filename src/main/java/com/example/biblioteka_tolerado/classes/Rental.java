package com.example.biblioteka_tolerado.classes;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanID")
    private Long loanID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books books;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "returned")
    private boolean returned;

    public Rental(){
    }
    public Rental(User user, Books books){
        //TODO this.loanDate = dzisiejsza data
        this.user = user;
        this.books = books;
        this.returned = false;
    }

    public void setLoanID(Long loanID) {
        this.loanID = loanID;
    }

    public Long getLoanID() {
        return loanID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Books getBook() {
        return books;
    }

    public void setBook(Books books) {
        this.books = books;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}