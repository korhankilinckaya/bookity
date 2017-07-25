package com.bookity.controller;

import com.bookity.model.Book;
import com.bookity.model.Status;
import com.bookity.model.User;
import com.bookity.service.BookService;
import com.bookity.service.UserService;
import org.apache.log4j.Logger;
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

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status addBook(@RequestBody Book book){
        try{
            bookService.addBook(book);
            return new Status(1,"Book added");
        } catch (Exception e) {
            logger.error(e);
            return new Status(0, e.toString());
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> getBookList() {
        List<Book> bookList = null;
        try {
            bookList = bookService.getBookList();
        } catch (Exception e) {
            logger.error(e);
        }
        return bookList;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Status deleteBook(@PathVariable("id") long id){
        try {
            bookService.deleteBook(id);
            return new Status(1,"book deleted");
        } catch (Exception e) {
            logger.error(e);
            return new Status(0,e.toString());
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status register(@RequestBody User user){
        try {
            userService.register(user);
            return new Status(1,"user registered");
        } catch (Exception e) {
            logger.error(e);
            return new Status(0,e.toString());
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status login(@RequestBody String email, String password){
        try {
            userService.login(email, password);
            return new Status(1,"user logged in");
        } catch (Exception e) {
            logger.error(e);
            return new Status(0,e.toString());
        }
    }

    @RequestMapping(value = "/buy/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status buy(@RequestBody long userId, @RequestBody long bookId, @RequestBody long numberOfBooks){
        try {

            userService.buy(userId, bookId, numberOfBooks);
            return new Status(1,"user bought a book");
        } catch (Exception e) {
            logger.error(e);
            return new Status(0,e.toString());
        }
    }

}
