package com.bookity.dao;

import com.bookity.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by korhan
 */
public class BookDaoImpl implements BookDao {

    @Autowired
    SessionFactory sessionFactory;

    Session session = null;
    Transaction tx = null;

    @Override
    public boolean addBook(Book book) throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(book);
        tx.commit();
        session.close();
        return false;
    }

    @Override
    public Book getBookById(long id) throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Book book = (Book) session.get(Book.class, new Long(id));
        tx.commit();
        return book;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getBookList() throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<Book> bookList = session.createCriteria(Book.class).list();
        tx.commit();
        session.close();

        return bookList;
    }

    @Override
    public boolean deleteBook(long id) throws Exception {
        session = sessionFactory.openSession();
        Object book = session.load(Book.class, id);
        tx = session.getTransaction();
        session.beginTransaction();
        session.delete(book);
        tx.commit();
        return false;
    }

    @Override
    public boolean updateBook(long id) throws Exception {
        session = sessionFactory.openSession();
        Object book = session.load(Book.class, id);
        tx = session.getTransaction();
        session.beginTransaction();
        session.update(book);
        tx.commit();
        return false;
    }
}