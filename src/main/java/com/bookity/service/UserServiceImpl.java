package com.bookity.service;

import com.bookity.dao.UserDao;
import com.bookity.dto.BookDTO;
import com.bookity.dto.UserDTO;
import com.bookity.model.Book;
import com.bookity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * Created by korhan
 */

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public boolean register(UserDTO user) throws Exception{
        user.setUserPassword(user.getUserPassword());
        return userDao.register(user);
    }

    @Override
    public boolean login(UserDTO user) throws Exception {
        return userDao.login(user);
    }

    @Override
    public List<User> getUserList() throws Exception {
        return userDao.getUserList();
    }

}
