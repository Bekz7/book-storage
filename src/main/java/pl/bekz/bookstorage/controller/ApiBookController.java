package pl.bekz.bookstorage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bekz.bookstorage.entity.Book;
import pl.bekz.bookstorage.repository.BookRepository;

import java.util.List;

@RequestMapping("/api")
@RestController
@Slf4j
public class ApiBookController {

    private final BookRepository bookRepository;

    public ApiBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookRepository.save(book);
            return ResponseEntity.ok("Book " + book + " added successfully.");
        } catch (Exception e) {
            log.debug("Could not commit the transaction while adding new book.", e);
            return ResponseEntity.badRequest().body("Could not commit transaction. Please validate input rules");
        }
    }
}
