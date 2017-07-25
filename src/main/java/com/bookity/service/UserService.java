package com.bookity.service;

import com.bookity.model.Book;
import com.bookity.model.User;

/**
 * Created by korhan
 */
public interface UserService {

    public boolean register(User user) throws Exception;
    public boolean login(String email, String password) throws Exception;
    public boolean buy(long userId, long bookId, long numberOfBooks) throws Exception;
}
