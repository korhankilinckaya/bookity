package com.bookity.test;

import com.bookity.controller.ServiceController;
import com.bookity.dto.BookDTO;
import com.bookity.dto.UserDTO;
import com.bookity.model.Book;
import com.bookity.model.SoldBooks;
import com.bookity.model.Status;
import com.bookity.model.User;
import com.bookity.service.BookService;
import com.bookity.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceController.class, loader = AnnotationConfigContextLoader.class)
public class ServiceControllerTest {
    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    private BookDTO book;
    private UserDTO user;

    static final Logger logger = Logger.getLogger(ServiceController.class);

    @BeforeClass
    public void initializeDTOs(){
        book = new BookDTO();
        book.setBookName("korhan");
        book.setBookWriter("korhan");
        book.setCategory(1);
        book.setISBN("1234");
        book.setStock(10);

        user = new UserDTO();
        user.setUserEmail("korhan");
        user.setUserName("korhan");
        user.setUserPassword("korhan123");
    }


    @Test
    public void register(UserDTO user){
        try {
            userService.register(user);
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void login(UserDTO user){
        try {
            userService.login(user);
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void getUserList() {
        try {
            List<User> userList = userService.getUserList();
            assertTrue(0<userList.size());
        } catch (Exception e){
            assertNull(e);
        }
    }

    //BOOK

    @Test
     public void addBook(BookDTO book){
        try {
            for(int i=0;i<3;i++){
                bookService.addBook(book);
            }
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void getBookById(){
        try {
            bookService.getBookById(1);
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void getBookList(int size) {
        try {
            List<Book> bookList = bookService.getBookList(10);
            assertTrue(0<bookList.size());
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void deleteBook(BookDTO book){
        try {
            book.setId(2);
            bookService.deleteBook(book);
            Book bookEntity = bookService.getBookById(3);
            assertNull(bookEntity);
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void updateBook(BookDTO book){
        try {
            book.setId(3);
            book.setBookWriter("korhanUpdated");
            bookService.updateBook(book);
            Book bookEntity = bookService.getBookById(3);
            assertEquals("korhanUpdated",bookEntity.getBookWriter());
        } catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void buy(BookDTO book){
        try {
            book.setUserId(1);
            book.setId(1);
            bookService.buy(book);
            List<SoldBooks> bookList = bookService.listOrderedBooks(1);

            List<SoldBooks> bookListOfUserAndBook = new ArrayList<SoldBooks>();

            for(int i=0;i<bookList.size();i++){
                if(bookList.get(i).getBookId()==book.getId() && bookList.get(i).getUserId()==book.getUserId()){
                    bookListOfUserAndBook.add(bookList.get(i));
                }
            }

            assertEquals(1,bookList.get(bookList.size()-1));
        } catch (Exception e){
            assertNull(e);
        }
    }


    @Test
    public void listOrderedBooks(int id) {
        try {
            List<SoldBooks> bookList = bookService.listOrderedBooks(1);
            assertTrue(0<bookList.size());
        } catch (Exception e){
            assertNull(e);
        }
    }

}
