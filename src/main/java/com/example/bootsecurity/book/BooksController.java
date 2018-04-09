package com.example.bootsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller that handles requests for the books endpoint at /books
 *
 * @author zuzhi
 * @date 04/04/2018
 */
@RestController
@RequestMapping("/books")
public class BooksController {

    private BookRepository bookRepository;

    @Autowired
    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(method = GET)
    public @ResponseBody
    List<Book> findAll() {
        return bookRepository.findAll();
    }

    @RequestMapping(method = POST)
    public @ResponseBody
    Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}
