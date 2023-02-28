package com.ali_bookStore.bookStore.services;

import com.ali_bookStore.bookStore.models.Book;
import com.ali_bookStore.bookStore.repositories.BookRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getSingleBook (ObjectId id){

        return bookRepository.findById(id);
    }

    public Book postBook(String title,String author,String description){
       Book book = new Book(title,author,description);
        bookRepository.insert(book);
       return book;
    }
    public Book putBook(ObjectId id ,Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
            Book _book = bookData.get();
            _book.setTitle(book.getTitle());
            _book.setAuthor(book.getAuthor());
            _book.setDescription(book.getDescription());
           return bookRepository.save(_book);
    }
    public void deleteBook(ObjectId id){
        bookRepository.deleteById(id);
    }
}
