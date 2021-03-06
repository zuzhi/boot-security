package com.zuzhi.corespring;

import com.zuzhi.corespring.book.Book;
import com.zuzhi.corespring.book.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Book bookCode = new Book("Code", "Charles Petzold");
    private Book bookRefactoring = new Book("Refactoring", "Martin Fowler");

    @Before
    public void setUp() {
        testEntityManager.persist(bookCode);
        testEntityManager.persist(bookRefactoring);
    }

    @Test
    public void testFindByTitle() {
        List<Book> booksWithTitleCode = bookRepository.findByTitle("Code");
        assertThat(booksWithTitleCode, contains(bookCode));
    }

    @Test
    public void testFindByAuthor() {
        List<Book> booksWithAuthorMartinFowler = bookRepository.findByAuthor("Martin Fowler");
        assertThat(booksWithAuthorMartinFowler, contains(bookRefactoring));
    }
}
