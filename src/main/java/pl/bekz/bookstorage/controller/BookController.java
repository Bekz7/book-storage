package pl.bekz.bookstorage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.bekz.bookstorage.entity.Book;
import pl.bekz.bookstorage.repository.BookRepository;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/index")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddNewBookForm(Book book) {
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.debug(errorMessageForAddBook(book, result));
            return "add-book";
        }
        this.bookRepository.save(book);
        return "redirect:/index";
    }

    private String errorMessageForAddBook(Book book, BindingResult result) {
        return "Error occurred while adding new book: "
                + book + " Exceptions["
                + result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
    }
}
