package models;

import java.time.LocalDate;

public class Entry {

    private String name;
    private String comment;
    private String email;
    private LocalDate date;

    public Entry(String name, String comment, String email, LocalDate date) {
        this.name = name;
        this.comment = comment;
        this.email = email;
        this.date = date;
    }

    public Entry setName(String name) {
        this.name = name;
        return this;
    }

    public Entry setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Entry setEmail(String email) {
        this.email = email;
        return this;
    }

    public Entry setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate() {
        return date;
    }
}
