package ru.otus.otusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.otusspring.repositories.BookMongoRepository;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookMongoRepository repository;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("books", repository.findAll());

        return "index";
    }
}
