package com.zuzhi.corespring.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zuzhi
 * @date 09/04/2018
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class BookNotFoundException extends RuntimeException {

    BookNotFoundException(String message) {
        super(message);
    }
}