package com.example.corespring.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zuzhi
 * @date 04/04/2018
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * fetch book by title
     *
     * @param title book title
     * @return books with the given title
     */
    List<Book> findByTitle(@Param("title") String title);

    /**
     * fetch book by author
     *
     * @param author author of the book
     * @return books with the given author
     */
    List<Book> findByAuthor(@Param("author") String author);
}
