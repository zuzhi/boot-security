package com.example.bootsecurity.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Controller that handles requests for the books endpoint at /books
 *
 * @author zuzhi
 * @date 04/04/2018
 */
@RestController
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = GET)
    public Page<Book> findAll(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page - 1, size);
        return bookService.findAll(pageRequest);
    }

    @RequestMapping(method = GET, value = "/{bookId}")
    public Book findById(@PathVariable Long bookId) {
        /// Use Method reference to throw new BookNotFoundException, but only works without arguments
        // return bookService.findById(bookId).orElseThrow(BookNotFoundException::new);

        ///
        // return bookService.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));


        return bookService.findById(bookId).orElseThrow(
                () -> new BookNotFoundException("Book [" + bookId + "] not found"));
    }

    @RequestMapping(method = POST, consumes = "application/json")
    public ResponseEntity<Book> saveBook(@RequestBody Book book,
                                         UriComponentsBuilder ucb) {
        bookService.save(book);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/books/")
                .path(String.valueOf(book.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = PUT, value = "/{bookId}", consumes = "application/json")
    public Book update(@RequestBody Book book,
                           @PathVariable Long bookId) {
        return bookService.update(book, bookId);
    }

    @RequestMapping(method = PATCH, value = "/{bookId}", consumes = "application/json")
    public Book updateBook(@RequestBody Map<String, String> updates,
                           @PathVariable Long bookId) {
        return bookService.update(updates, bookId);
    }
}
