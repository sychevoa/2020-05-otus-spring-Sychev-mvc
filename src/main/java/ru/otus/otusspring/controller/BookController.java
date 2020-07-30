package ru.otus.otusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.otusspring.exception.BookNotFoundException;
import ru.otus.otusspring.model.Book;
import ru.otus.otusspring.service.BookService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("books", service.getAll());

        return "index";
    }

    @GetMapping("/initbook")
    public String initBook(Book book) {

        return "add-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }

        String bookId = service.add(book);
        model.addAttribute("bookId", bookId);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id, Model model) {
        Book book = service.getOne(id);

        model.addAttribute("book", book);
        service.delete(id);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Book book = service.getOne(id);
        model.addAttribute("book", book);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String editBook(@PathVariable String id, @Valid Book book,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        service.save(book);
        model.addAttribute("books", service.getAll());

        return "redirect:/";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
