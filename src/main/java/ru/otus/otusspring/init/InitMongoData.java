package ru.otus.otusspring.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import ru.otus.otusspring.model.User;
import ru.otus.otusspring.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class InitMongoData {

    private final UserRepository repository;

    @Bean
    private CommandLineRunner initData() {
        return args -> {
            User admin = new User();
            admin.setName("admin");
            admin.setPassword("pass");
            admin.setRole("ADMIN");

            User user = new User();
            user.setName("user");
            user.setPassword("password");
            user.setRole("USER");

            if (!repository.exists(Example.of(admin))) {
                repository.save(admin);
            }

            if (!repository.exists(Example.of(user))) {
                repository.save(user);
            }
        };
    }
}
