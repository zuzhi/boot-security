package com.example.bootsecurity.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    Book save(Book book) {
        return bookRepository.save(book);
    }
}
