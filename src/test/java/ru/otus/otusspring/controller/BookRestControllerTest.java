package ru.otus.otusspring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.otusspring.model.Book;
import ru.otus.otusspring.service.BookService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
@DisplayName("BookRestController должен: ")
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("получать ожидаемую книгу по ID")
    public void shouldReturnExpectedBookById() throws Exception {
        given(bookService.getOne("1")).willReturn(new Book("Anna Karenina", "Leo Tolstoy", "novel", "no comment"));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Anna Karenina"));
    }
}
