package ru.otus.otusspring.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.otusspring.model.Book;
import ru.otus.otusspring.repositories.BookMongoRepository;

import java.util.List;

import static org.mockito.BDDMockito.given;

@DisplayName("BookRestController должен: ")
@WebFluxTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookMongoRepository repository;

    private Book bookOne;
    private Book bookTwo;

    @BeforeEach
    void setUp() {
        bookOne = new Book("Test Book One", "Test Author One", "some genre one", "no comment");
        bookTwo = new Book("Test Book Two", "Test Autho Twor", "some genre two", "with comment");
    }

    @DisplayName("возвращать все имеющиеся книги")
    @Test
    void shouldReturnAllBooks() {
        List<Book> books = List.of(bookOne, bookTwo);
        given(repository.findAll()).willReturn(Flux.just(bookOne, bookTwo));

        webTestClient.get().uri("/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .hasSize(2)
                .isEqualTo(books);
    }

}
