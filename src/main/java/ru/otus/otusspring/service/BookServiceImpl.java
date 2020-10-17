package ru.otus.otusspring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(commandKey = "commonBookKey", fallbackMethod = "fallBackGetBook")
    public Book getOne(String id) {
        Optional<Book> bookOptional = bookRepo.findById(id);

        return bookOptional.orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    @HystrixCommand(commandKey = "commonBookKey", fallbackMethod = "fallBackGetAllBooks")
    public List<Book> getAll() {

        return bookRepo.findAll();
    }

    @Override
    public void delete(String id) {

        bookRepo.deleteById(id);
    }

    @Override
    @HystrixCommand(commandKey = "commonBookKey", fallbackMethod = "fallBackSaveBook")
    public Book save(Book book) {
        
        return bookRepo.save(book);
    }

    @Override
    public boolean exists(String id) {

        return bookRepo.existsById(id);
    }

    private Book fallBackGetBook(String id) {

        return new Book(id, "n/a", "n/a", "n/a");
    }

    private List<Book> fallBackGetAllBooks() {

        return List.of(fallBackGetBook("n/a"));
    }

    private Book fallBackSaveBook(Book book) {

        return fallBackGetBook("n/a");
    }

}
