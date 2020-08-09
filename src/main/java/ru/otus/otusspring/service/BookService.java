package ru.otus.otusspring.service;

import ru.otus.otusspring.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getOne(String id);

    Book save(Book book);

    void delete(String id);

    boolean exists(String id);
}
