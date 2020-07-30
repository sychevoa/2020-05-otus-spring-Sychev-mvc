package ru.otus.otusspring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.otusspring.model.Book;

public interface BookMongoRepository extends MongoRepository<Book, String> {
}
