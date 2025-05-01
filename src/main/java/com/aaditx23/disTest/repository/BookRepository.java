package com.aaditx23.disTest.repository;

import com.aaditx23.disTest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // JpaRepository provides these by default
    // - save(Book book)
    // - findAll()
    // - findById(Long id)
    // - deleteById(Long id)

    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByGenre(String genre);
    List<Book> findBooksByPublishedYear(int publishedYear);
}
