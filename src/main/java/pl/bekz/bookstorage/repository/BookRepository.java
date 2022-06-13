package pl.bekz.bookstorage.repository;

import org.springframework.data.repository.Repository;
import pl.bekz.bookstorage.entity.Book;

import java.util.List;

public interface BookRepository extends Repository<Book, Integer> {
    List<Book> findAll();

    Book save(Book book);

}
