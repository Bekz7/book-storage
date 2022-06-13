package pl.bekz.bookstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Pattern(regexp = "^[A].*\\s[A][a-z]*",
            message = "Invalid Author firstname and lastname: '${validatedValue}'. Both should be started from capital A.")
    private String author;
    @NotBlank(message = "Cannot be blank.")
    private String title;
    @NotBlank(message = "Cannot be blank.")
    private String isbn;

    public Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }
}
