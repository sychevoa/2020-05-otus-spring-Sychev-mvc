package ru.otus.otusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.otusspring.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("books", service.getAll());

        return "index";
    }
}
