package com.example.corespring;

import com.example.corespring.book.Book;
import com.example.corespring.book.BookRepository;
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

    private Book bookSpringInAction = new Book("Spring in Action", "Craig Walls");
    private Book bookRefactoring = new Book("Refactoring", "Martin Fowler");

    @Before
    public void setUp() {
        testEntityManager.persist(bookSpringInAction);
        testEntityManager.persist(bookRefactoring);
    }

    @Test
    public void testFindByTitle() {
        List<Book> booksWithTitleSpringInAction = bookRepository.findByTitle("Spring in Action");
        assertThat(booksWithTitleSpringInAction, contains(bookSpringInAction));
    }

    @Test
    public void testFindByAuthor() {
        List<Book> booksWithAuthorMartinFowler = bookRepository.findByAuthor("Martin Fowler");
        assertThat(booksWithAuthorMartinFowler, contains(bookRefactoring));
    }
}
