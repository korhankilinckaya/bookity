package com.bookity.util;

import com.bookity.dto.BaseDTO;
import com.bookity.dto.BookDTO;
import com.bookity.dto.UserDTO;
import com.bookity.model.BaseEntity;
import com.bookity.model.Book;
import com.bookity.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static String convertToHash(String password){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }

    public static BaseEntity convertDTOtoEntity(BaseDTO dto){
        if(dto instanceof UserDTO){
            User user = new User();
            user.setId(((UserDTO) dto).getId());
            user.setUserPassword(convertToHash(((UserDTO) dto).getUserPassword()));
            user.setLastRegistryDate(((UserDTO) dto).getLastRegistryDate());
            user.setUserEmail(((UserDTO) dto).getUserEmail());
            user.setUserName(((UserDTO) dto).getUserName());

            return user;
        }else if(dto instanceof BookDTO){
            Book book = new Book();
            book.setStock(((BookDTO) dto).getStock());
            book.setISBN(((BookDTO) dto).getISBN());
            book.setBookName(((BookDTO) dto).getBookName());
            book.setCategory(((BookDTO) dto).category);
            book.setBookWriter(((BookDTO) dto).getBookWriter());
            book.setId(((BookDTO) dto).getId());
            return book;
        }
        return null;
    }
}
