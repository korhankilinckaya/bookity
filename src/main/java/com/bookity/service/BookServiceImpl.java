package com.bookity.service;

import com.bookity.dao.BookDao;
import com.bookity.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by korhan
 */
public class BookServiceImpl implements BookService{

    @Autowired
    BookDao bookDao;

    @Override
    public boolean addBook(Book book) throws Exception{
        return bookDao.addBook(book);
    }

    @Override
    public Book getBookById(long id) throws Exception {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getBookList() throws Exception {
        return bookDao.getBookList();
    }

    @Override
    public boolean deleteBook(long id) throws Exception {
        return bookDao.deleteBook(id);
    }

    @Override
    public boolean updateBook(long id) throws Exception {
        return bookDao.updateBook(id);
    }

}
