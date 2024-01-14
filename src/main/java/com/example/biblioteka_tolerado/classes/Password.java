//package com.example.biblioteka_tolerado.classes;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "passwords")
//public class Password {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String password;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userID")
//    private User user;
//
//    public Password() {
//    }
//
//    public Password(User user, String passwordValue) {
//        this.user = user;
//        this.password = passwordValue;
//    }
//    //gettery settery
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    // Metoda do zapisu hasła do bazy danych
//    public static void savePassword(User user, String password) {
//        Configuration configuration = new Configuration().configure();
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Password newPassword = new Password(user, password);
//        session.save(newPassword);
//
//        transaction.commit();
//        session.close();
//        sessionFactory.close();
//    }
//    public String getPassword(){
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
//
////    // Metoda do pobrania hasła z bazy danych na podstawie id użytkownika
////    public static String getPasswordByUsername(String username) {
////        Configuration configuration = new Configuration().configure();
////        SessionFactory sessionFactory = configuration.buildSessionFactory();
////        Session session = sessionFactory.openSession();
////
////        Password password = session.createQuery("FROM Password WHERE userID = :username", Password.class)
////                .setParameter("username", username)
////                .uniqueResult();
////
////        session.close();
////        sessionFactory.close();
////
////        return (password != null) ? password.getPasswordValue() : null;
////    }
//
