package com.abbas.bookstore.controller;


import com.abbas.bookstore.model.Book;
import com.abbas.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/admin/create-book")
    public ResponseEntity<?> createBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-book")
    public ResponseEntity<?> updateBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete-book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-books")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }
}
