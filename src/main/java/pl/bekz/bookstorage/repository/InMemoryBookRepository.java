package pl.bekz.bookstorage.repository;

import pl.bekz.bookstorage.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class InMemoryBookRepository implements BookRepository {
    Map<Integer, Book> map = new HashMap<>();

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Book save(Book book) {
        requireNonNull(book);
        return map.put(generateId(), book);
    }

    private int generateId() {
        return map.size();
    }
}
