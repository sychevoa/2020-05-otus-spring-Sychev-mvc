package ru.otus.otusspring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.otusspring.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);
}
