package com.example.biblioteka_tolerado.services_interfaces;

import java.util.List;

public class BookResponse {
    private Integer bookId;
    private String title;
    private String language;
    private List<String> authors;  // Dodaj listę autorów
    private Integer availability;

    public BookResponse(Integer bookId, String title, String language, List<String> authors, Integer availability) {
        this.bookId = bookId;
        this.title = title;
        this.language = language;
        this.authors = authors;
        this.availability = availability;
    }

    // Dodaj gettery i settery
}
