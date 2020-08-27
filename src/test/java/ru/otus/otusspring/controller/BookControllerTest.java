package ru.otus.otusspring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.otusspring.model.Book;
import ru.otus.otusspring.repositories.UserRepository;
import ru.otus.otusspring.service.BookService;
import ru.otus.otusspring.service.UserDetailsServiceImpl;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({BookController.class, UserDetailsServiceImpl.class})
@DisplayName("BookController должен: ")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserRepository repository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_USER"}
    )

    @Test
    @DisplayName("выводить все книги на главной странице")
    public void test() throws Exception {
        given(bookService.getAll()).willReturn(List.of(new Book("Anna Karenina", "Leo Tolstoy", "novel", List.of("no comment"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(content().string(containsString("Anna Karenina")));
    }
}
