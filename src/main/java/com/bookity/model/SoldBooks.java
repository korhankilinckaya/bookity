package com.bookity.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "SOLD_BOOKS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SoldBooks extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="user_id")
    private long userId;

    @Column(name="book_id")
    private long bookId;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", nullable = false, length = 10)
    private Date createdDate;

    @Column(name = "number_of_books", nullable = false)
    private long numberOfBooks;

    @Column(name = "payment_completed", nullable = true)
    private long paymentCompleted;

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(long numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public long getPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(long paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
