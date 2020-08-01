package ru.otus.otusspring.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String id) {
        super("Book " + id + " not found");
    }
}
