package com.example.bootsecurity.book;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author zuzhi
 * @date 04/04/2018
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    List<Book> findAll() {
        return bookRepository.findAll();
    }

    Page<Book> findAll(Pageable pageRequest) {
        return bookRepository.findAll(pageRequest);
    }

    Optional<Book> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    void save(Book book) {
        bookRepository.save(book);
    }

    Book update(Book book, Long bookId) {
        Preconditions.checkNotNull(book);
        // TODO NPE if bookId does not supplied, so RequestBody should be validated
        Preconditions.checkState(book.getId().equals(bookId));
        Preconditions.checkNotNull(bookRepository.findById(bookId));
        return bookRepository.save(book);
    }

    Book update(Map<String, String> updates, Long bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book [" + bookId + "] not found"));

        updates.keySet()
                .forEach(key -> {
                    switch (key) {
                        case "author":
                            book.setAuthor(updates.get(key));
                            break;
                        case "title":
                            book.setTitle(updates.get(key));
                            break;
                        default:
                            break;
                    }
                });
        return bookRepository.save(book);
    }
}
