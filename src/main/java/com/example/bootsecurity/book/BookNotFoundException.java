package com.example.bootsecurity.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zuzhi
 * @date 09/04/2018
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class BookNotFoundException extends RuntimeException {
    private Long bookId;

    BookNotFoundException() {
    }

    BookNotFoundException(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
