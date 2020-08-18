package ru.otus.otusspring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.otusspring.model.Book;

public interface BookMongoRepository extends ReactiveMongoRepository<Book, String> {
}
