package com.bookity.dao;

import com.bookity.Exceptions.UserNotLoggedInException;
import com.bookity.dto.BookDTO;
import com.bookity.model.Book;
import com.bookity.model.SoldBooks;
import com.bookity.model.User;
import com.bookity.util.Util;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by korhan
 */
public class BookDaoImpl implements BookDao {

    @Autowired
    SessionFactory sessionFactory;

    Session session = null;
    Transaction tx = null;

    static final Logger logger = Logger.getLogger(BookDaoImpl.class);


    @Override
    public boolean addBook(BookDTO book) throws Exception{

        session = sessionFactory.openSession();

        if(!isUserLoggedIn(book.getUserId())){
            throw new UserNotLoggedInException();
        }

        tx = session.beginTransaction();
        session.save(Util.convertDTOtoEntity(book));
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
    public List<Book> getBookList(int size) throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<Book> bookList = session.createCriteria(Book.class).setMaxResults(size).list();
        tx.commit();
        session.close();

        return bookList;
    }

    @Override
    public boolean deleteBook(BookDTO book) throws Exception {

        session = sessionFactory.openSession();

        if(!isUserLoggedIn(book.getUserId())){
            throw new UserNotLoggedInException();
        }

        Object bookEntity = session.load(Book.class, book.getId());

        tx = session.getTransaction();
        session.beginTransaction();
        session.delete(bookEntity);
        tx.commit();
        return false;
    }

    @Override
    public boolean updateBook(BookDTO book) throws Exception {

        session = sessionFactory.openSession();

        if(!isUserLoggedIn(book.getUserId())){
            throw new UserNotLoggedInException();
        }

        Object bookEntity = session.load(Book.class, book.getId());

        tx = session.getTransaction();
        session.beginTransaction();
        session.update(bookEntity);
        tx.commit();
        return false;
    }

    @Override
    public Long[] buy(BookDTO book) throws Exception{

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        if(!isUserLoggedIn(book.getUserId())){
            throw new UserNotLoggedInException();
        }

        SoldBooks soldBooks = new SoldBooks();

        Book bookEntity = (Book) session.load(Book.class, book.getId());
        User userEntity = (User) session.load(User.class, book.getUserId());

        soldBooks.setBook(bookEntity);
        soldBooks.setUser(userEntity);
        soldBooks.setCreatedDate(new Date());
        soldBooks.setNumberOfBooks(book.getStock());

        session.save(soldBooks);
        tx.commit();
        session.close();

        Long[] ids = new Long[2];
        ids[0]=book.getUserId();
        ids[1]=book.getId();

        return ids;
    }

    @Override
    public boolean completePayment(Long[] id) throws Exception{

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query= session.
                createQuery("from SoldBooks s where s.pk.user.id=:userId and s.pk.book.id =:bookId");
        query.setParameter("userId", id[0]);
        query.setParameter("bookId", id[1]);

        logger.error("ids------------- user: "+id[0]+"  book: "+id[1]);

        SoldBooks soldBooks = (SoldBooks) query.list().get(0);
        soldBooks.setPaymentCompleted(1);

        session.update(soldBooks);
        tx.commit();
        session.close();
        return false;
    }

    private boolean isUserLoggedIn(long userId) throws UserNotLoggedInException{
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date onehr = calendar.getTime();
        User userEntity = (User) session.load(User.class, userId);
        if(userEntity.getLastRegistryDate()== null){
            throw new UserNotLoggedInException();
        }
        else if(userEntity.getLastRegistryDate().compareTo(onehr) < 0)
        {
            return false;
        }else
            return true;
    }
}