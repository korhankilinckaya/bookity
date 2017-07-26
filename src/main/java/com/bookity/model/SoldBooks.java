package com.bookity.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "SOLD_BOOKS", catalog = "bookity")
@AssociationOverrides({
        @AssociationOverride(name = "pk.User",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.Book",
                joinColumns = @JoinColumn(name = "book_id")) })
public class SoldBooks extends BaseEntity  implements java.io.Serializable {

    private SoldBooksId pk = new SoldBooksId();

    @Temporal(TemporalType.DATE)
    @Column(name = "createdDate", nullable = false, length = 10)
    private Date createdDate;

    @Column(name = "numberOfBooks", nullable = false)
    private long numberOfBooks;

    @Column(name = "paymentCompleted", nullable = true)
    private long paymentCompleted;

    @EmbeddedId
    public SoldBooksId getPk() {
        return pk;
    }

    public void setPk(SoldBooksId pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return pk.getUser();
    }

    public void setUser(User user) {
        pk.setUser(user);
    }

    @Transient
    public Book getBook() {
        return pk.getBook();
    }

    public void setBook(Book book) {
        pk.setBook(book);
    }

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

}
