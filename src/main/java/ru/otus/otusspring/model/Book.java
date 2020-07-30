package ru.otus.otusspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String genre;
    private List<String> comments;

    public Book(String title, String author, String genre, List<String> comments) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }
}
