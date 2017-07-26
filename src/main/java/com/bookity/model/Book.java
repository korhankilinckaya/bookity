package com.bookity.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by korhan
 */
@Entity
@Table(name="BOOK")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="book_id")
    private long id;

    @Column(name="bookName", nullable = false, length = 40)
    private String bookName;

    @Column(name="bookWriter", nullable = false, length = 40)
    private String bookWriter;

    @Column(name="ISBN", unique = true, nullable = false, length = 10)
    private String ISBN;

    @Column(name="stock")
    private long stock;

    @Column(name="category", nullable = false)
    private long category;

    @ElementCollection(targetClass=SoldBooks.class)
    private Set<SoldBooks> soldBooks = new HashSet<SoldBooks>(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.Book", cascade = CascadeType.ALL)
    public Set<SoldBooks> getSoldBooks() {
        return this.soldBooks;
    }

    public void setSoldBooks(Set<SoldBooks> soldBooks) {
        this.soldBooks = soldBooks;
    }
}
