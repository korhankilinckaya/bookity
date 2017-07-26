package com.bookity.service;

import com.bookity.dao.BookDao;
import com.bookity.dto.BookDTO;
import com.bookity.external.PaymentServiceExternal;
import com.bookity.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by korhan
 */
public class BookServiceImpl implements BookService{

    @Autowired
    BookDao bookDao;

    @Autowired
    PaymentServiceExternal paymentExt;

    @Override
    public boolean addBook(BookDTO book) throws Exception{
        return bookDao.addBook(book);
    }

    @Override
    public Book getBookById(long id) throws Exception {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getBookList(int size) throws Exception {
        return bookDao.getBookList(size);
    }

    @Override
    public boolean deleteBook(BookDTO book) throws Exception {
        return bookDao.deleteBook(book);
    }

    @Override
    public boolean updateBook(BookDTO book) throws Exception {
        return bookDao.updateBook(book);
    }

    @Override
    public boolean buy(BookDTO book) throws Exception {
        Long[] ids = bookDao.buy(book);
        boolean isPaid = paymentExt.pay();

        boolean paymentCompleted = false;

        if(isPaid){
            paymentCompleted = bookDao.completePayment(ids);
        }
        return paymentCompleted;
    }
}
