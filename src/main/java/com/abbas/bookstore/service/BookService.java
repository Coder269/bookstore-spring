package com.abbas.bookstore.service;

import com.abbas.bookstore.model.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);

    void deleteBookById(Long id);

    List<Book> findAllBooks();
}
