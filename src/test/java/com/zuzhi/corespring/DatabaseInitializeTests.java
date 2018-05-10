package com.zuzhi.corespring;

import com.zuzhi.corespring.book.Book;
import com.zuzhi.corespring.book.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DatabaseInitializeTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByTitle() {
        bookRepository.save(new Book("Hackers and Painters", "Paul Graham"));

        List<Book> booksWithTitleSpringInAction = bookRepository.findByTitle("Hackers and Painters");
        assertEquals(booksWithTitleSpringInAction.size(), 1);
    }
}
