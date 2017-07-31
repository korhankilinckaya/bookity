package com.bookity.service;

import com.bookity.dto.BookDTO;
import com.bookity.model.Book;
import com.bookity.model.SoldBooks;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by korhan
 */


public interface BookService {

    public boolean addBook(BookDTO book) throws Exception;
    public Book getBookById(long id) throws Exception;
    public List<Book> getBookList(int size) throws Exception;
    public boolean deleteBook(BookDTO book) throws Exception;
    public boolean updateBook(BookDTO book) throws Exception;
    public boolean buy(BookDTO book) throws Exception;
    public List<SoldBooks> listOrderedBooks(long id) throws Exception;
}
