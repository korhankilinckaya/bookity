package com.bookity.dao;

import com.bookity.dto.UserDTO;
import com.bookity.model.Book;
import com.bookity.model.User;
import com.bookity.util.Util;
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
    public boolean register(UserDTO dto) throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        session.save(Util.convertDTOtoEntity(dto));
        tx.commit();
        session.close();
        return false;
    }

    @Override
    public boolean login(UserDTO user) throws Exception{
        session = sessionFactory.openSession();
        tx = session.getTransaction();
        session.beginTransaction();

        Query query= session.
                createQuery("from User where userEmail=:email and userPassword =:password");
        query.setParameter("email", user.getUserEmail());
        query.setParameter("password", Util.convertToHash(user.getUserPassword()));


        User userEntity = null;

        if(query.list()==null || query.list().size()==0){
            return false;
        }

        userEntity = (User) query.list().get(0);

        if(user != null){
            userEntity.setLastRegistryDate(new Date());
        }

        session.update(userEntity);
        tx.commit();
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserList() throws Exception{
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<User> userList = session.createCriteria(User.class).list();
        tx.commit();
        session.close();

        return userList;
    }

}