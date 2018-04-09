package com.example.bootsecurity.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zuzhi
 * @date 04/04/2018
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
