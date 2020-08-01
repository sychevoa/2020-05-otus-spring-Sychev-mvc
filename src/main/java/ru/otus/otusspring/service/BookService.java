package ru.otus.otusspring.service;

import ru.otus.otusspring.model.Book;

import java.util.List;

public interface BookService {
    Book getOne(String id);

    List<Book> getAll();

    String add(Book book);

    void delete(String id);

    Book save(Book book);
}
