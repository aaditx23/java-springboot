package com.aaditx23.disTest.controller;

import com.aaditx23.disTest.model.Book;
import com.aaditx23.disTest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Book> addBook(
            @RequestBody Book newBook
    ){
        Book savedBook = bookRepository.save(newBook);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> editBookById(
            @PathVariable int id,
            @RequestBody Book newBook
    ){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setGenre(newBook.getGenre());
            book.setPublishedYear(newBook.getPublishedYear());

            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(
            @PathVariable int id
    ){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.delete(book.get());
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping
    public List<Book> getBooksByAuthor(
            @RequestBody String authorName
    ){
        return bookRepository.findBooksByAuthor(authorName);
    }

    @GetMapping
    public List<Book> getBooksByGenre(
            @RequestBody String genre
    ){
        return bookRepository.findBooksByGenre(genre);
    }

    @GetMapping
    public List<Book> getBooksByPublishedYear(
            @RequestBody int year
    ){
        return bookRepository.findBooksByPublishedYear(year);
    }

}
