package com.bookity.service;

import com.bookity.dto.BookDTO;
import com.bookity.dto.UserDTO;
import com.bookity.model.Book;
import com.bookity.model.User;

import java.util.List;

/**
 * Created by korhan
 */
public interface UserService {

    public boolean register(UserDTO user) throws Exception;
    public boolean login(UserDTO user) throws Exception;
    public List<User> getUserList() throws Exception;
}
