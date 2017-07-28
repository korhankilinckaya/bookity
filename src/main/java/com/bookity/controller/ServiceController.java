package com.bookity.controller;

import com.bookity.dto.BookDTO;
import com.bookity.dto.UserDTO;
import com.bookity.model.Book;
import com.bookity.model.SoldBooks;
import com.bookity.model.Status;
import com.bookity.model.User;
import com.bookity.service.BookService;
import com.bookity.service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/")
public class ServiceController {
    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    static final Logger logger = Logger.getLogger(ServiceController.class);

    //BOOK

    @RequestMapping(value = "/createBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status addBook(@RequestBody BookDTO book){
        try{
            bookService.addBook(book);
            return new Status(1,"Book added");
        } catch (Exception e) {
            logger.error(e);
            if(e instanceof ObjectNotFoundException){
                return new Status(0, "given ID not found");
            }
            return new Status(0, e.toString());
        }
    }

    @RequestMapping(value="/getBook/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Book getBookById(@PathVariable("id") long id){
        Book book = null;
        try {
            book = bookService.getBookById(id);

        } catch (Exception e) {
            logger.error(e);
        }
        return book;
    }

    @RequestMapping(value="/listBooks/size/{size}", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> getBookList(@PathVariable int size) {
        List<Book> bookList = null;
        try {
            bookList = bookService.getBookList(size);
        } catch (Exception e) {
            logger.error(e);
        }
        return bookList;
    }

    @RequestMapping(value="/deleteBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status deleteBook(@RequestBody BookDTO book){
        try {
            bookService.deleteBook(book);
            return new Status(1,"book deleted");
        } catch (Exception e) {
            logger.error(e);
            if(e instanceof ObjectNotFoundException){
                return new Status(0, "given ID not found");
            }
            return new Status(0,e.toString());
        }
    }

    @RequestMapping(value="/updateBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status updateBook(@RequestBody BookDTO book){
        try {
            bookService.updateBook(book);
            return new Status(1,"book updated");
        } catch (Exception e) {
            logger.error(e);
            if(e instanceof ObjectNotFoundException){
                return new Status(0, "given ID not found");
            }
            return new Status(0,e.toString());
        }
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status buy(@RequestBody BookDTO book){
        try {

            bookService.buy(book);
            return new Status(1,"user bought a book");
        } catch (Exception e) {
            logger.error(e);
            if(e instanceof ObjectNotFoundException){
                return new Status(0, "given ID not found");
            }
            return new Status(0,e.toString());
        }
    }


    // USER

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status register(@RequestBody UserDTO user){
        try {
            userService.register(user);
            return new Status(1,"user registered");
        } catch (Exception e) {
            logger.error(e);
            if(e instanceof ObjectNotFoundException){
                return new Status(0, "given ID not found");
            }
            return new Status(0,e.toString());
        }
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status login(@RequestBody UserDTO user){
        try {
            userService.login(user);
            return new Status(1,"user logged in");
        } catch (Exception e) {
            logger.error(e);
            if(e instanceof ObjectNotFoundException){
                return new Status(0, "given ID not found");
            }
            return new Status(0,e.toString());
        }
    }

    @RequestMapping(value="/listUsers", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUserList() {
        List<User> userList = null;
        try {
            userList = userService.getUserList();
        } catch (Exception e) {
            logger.error(e);
        }
        return userList;
    }

    @RequestMapping(value="/listOrderedBooks/userId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<SoldBooks> listOrderedBooks(@PathVariable("id") int id) {
        List<SoldBooks> bookList = null;
        try {
            bookList = bookService.listOrderedBooks(id);
        } catch (Exception e) {
            logger.error(e);
        }
        return bookList;
    }
}
