package ru.otus.otusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.otusspring.exception.BookNotFoundException;
import ru.otus.otusspring.model.Book;
import ru.otus.otusspring.repositories.BookMongoRepository;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookMongoRepository repository;

    @GetMapping("/books")
    public Flux<Book> getAll() {

        return repository.findAll();
    }

    @GetMapping("/book/{id}")
    public Mono<Book> get(@PathVariable String id) {

        return repository.findById(id);
    }

    @PostMapping("/book")
    public Mono<String> save(@Valid Book book) {

        return repository
                .save(book)
                .map(Book::getId);
    }

    @DeleteMapping("/book/{id}")
    public Mono<Void> delete(@PathVariable String id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new BookNotFoundException(id)))
                .flatMap(repository::delete);

    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
