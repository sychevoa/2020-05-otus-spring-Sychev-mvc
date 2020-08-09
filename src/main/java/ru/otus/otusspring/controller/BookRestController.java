package ru.otus.otusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.otusspring.exception.BookNotFoundException;
import ru.otus.otusspring.model.Book;
import ru.otus.otusspring.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService service;

    @GetMapping("/allbooks")
    public List<Book> getAll() {

        return service.getAll();
    }

    @GetMapping("/book/{id}")
    public Book get(@PathVariable String id) {

        return service.getOne(id);
    }

    @PostMapping("/book")
    public ResponseEntity<String> save(@Valid Book book) {
        String bookId = service.save(book).getId();

        return ResponseEntity.ok(bookId);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!service.exists(id))
            throw new BookNotFoundException(id);

        service.delete(id);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
