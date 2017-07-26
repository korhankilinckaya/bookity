package com.bookity.dao;

import com.bookity.dto.BookDTO;
import com.bookity.dto.UserDTO;
import com.bookity.model.Book;
import com.bookity.model.User;

/**
 * Created by korhan
 */
public interface UserDao {

    public boolean register(UserDTO user) throws Exception;
    public boolean login(UserDTO user) throws Exception;
}
