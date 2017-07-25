package com.bookity.dao;

import com.bookity.model.Book;
import com.bookity.model.SoldBooks;
import com.bookity.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by korhan
 */
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    Session session = null;
    Transaction tx = null;

    @Override
    public boolean register(User user) throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        return false;
    }

    @Override
    public boolean login(String email, String password) throws Exception{
        session = sessionFactory.openSession();
        tx = session.getTransaction();
        session.beginTransaction();

        Query query= session.
                createQuery("from User where userEmail=:email and userPassword =:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = (User) query.list().get(0);

        if(user != null){
            user.setLastRegistryDate(new Date());
        }

        session.update(user);
        tx.commit();
        return false;
    }

    @Override
    public boolean buy(long userId, long bookId, long numberOfBooks) throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        SoldBooks soldBooks = new SoldBooks();

        Book book = (Book) session.load(Book.class, bookId);
        User user = (User) session.load(User.class, userId);

        soldBooks.setBook(book);
        soldBooks.setUser(user);
        soldBooks.setCreatedDate(new Date());
        soldBooks.setNumberOfBooks(numberOfBooks);

        session.save(soldBooks);
        tx.commit();
        session.close();
        return false;
    }

}