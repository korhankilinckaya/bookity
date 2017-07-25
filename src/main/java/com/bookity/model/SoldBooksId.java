package com.bookity.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class SoldBooksId implements java.io.Serializable {

    private User user;
    private Book book;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}