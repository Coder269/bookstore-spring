package com.abbas.bookstore.service;

import com.abbas.bookstore.model.Book;
import com.abbas.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book){
     book.setAddTime(LocalDateTime.now());
     return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {return bookRepository.save(book); }
}
