package com.bookity.service;

import com.bookity.model.Book;

import java.util.List;

/**
 * Created by korhan
 */
public interface BookService {

    public boolean addBook(Book book) throws Exception;
    public Book getBookById(long id) throws Exception;
    public List<Book> getBookList() throws Exception;
    public boolean deleteBook(long id) throws Exception;
    public boolean updateBook(long id) throws Exception;
}
