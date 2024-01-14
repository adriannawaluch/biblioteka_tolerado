package com.example.biblioteka_tolerado.classes;
import jakarta.persistence.*;
import java.util.Date;

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
    @JoinColumn(name = "bookID")
    private Books books;

    @Column(name = "loan_date")
    private Date loanDate;

    @Column(name = "return_date")
    private Date returnDate;

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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}