package ru.otus.otusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.otusspring.exception.BookNotFoundException;
import ru.otus.otusspring.model.Book;
import ru.otus.otusspring.repositories.BookMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMongoRepository bookRepo;

    @Override
    public Book getOne(String id) {
        Optional<Book> bookOptional = bookRepo.findById(id);

        return bookOptional.orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public List<Book> getAll() {

        return bookRepo.findAll();
    }

    @Override
    public String add(Book book) {
        Book savedBook = bookRepo.save(book);

        return savedBook.getId();
    }

    @Override
    public void delete(String id) {

        bookRepo.deleteById(id);
    }

    @Override
    public Book save(Book book) {

        return bookRepo.save(book);
    }
}
