package com.ali_bookStore.bookStore.controllers;

import com.ali_bookStore.bookStore.models.Book;
import com.ali_bookStore.bookStore.services.BookService;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
    return new ResponseEntity<>(bookService.allBooks(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable ObjectId id){
        return new ResponseEntity<>(bookService.getSingleBook(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book ){
        try {
            return  new ResponseEntity<>(bookService.postBook(book.getTitle(), book.getAuthor(), book.getDescription()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
  }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") ObjectId id, @RequestBody Book book) {
try {
    return new ResponseEntity<>(bookService.putBook(id,book),HttpStatus.OK);
}catch (Exception e){
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") ObjectId id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
