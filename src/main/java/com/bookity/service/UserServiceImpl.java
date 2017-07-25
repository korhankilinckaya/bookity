package com.bookity.service;

import com.bookity.dao.UserDao;
import com.bookity.model.Book;
import com.bookity.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by korhan
 */
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public boolean register(User user) throws Exception{
        user.setUserPassword(convertToHash(user.getUserPassword()));
        return userDao.register(user);
    }

    @Override
    public boolean login(String email, String password) throws Exception {
        return userDao.login(email,convertToHash(password));
    }

    @Override
    public boolean buy(long userId, long bookId, long numberOfBooks) throws Exception {
        return userDao.buy(userId, bookId, numberOfBooks);
    }

    public String convertToHash(String password){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }


}
