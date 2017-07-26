package com.bookity.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by korhan
 */
@Entity
@Table(name="USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private long id;

    @Column(name="userName", nullable = false, length = 20)
    private String userName;

    @Column(name="userEmail", nullable = false, length = 20)
    private String userEmail;

    @Column(name="userPassword", nullable = false, length = 255)
    private String userPassword;

    @Column(name="lastRegistryDate")
    private Date lastRegistryDate;

    @ElementCollection(targetClass=SoldBooks.class)
    private Set<SoldBooks> soldBooks = new HashSet<SoldBooks>(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getLastRegistryDate() {
        return lastRegistryDate;
    }

    public void setLastRegistryDate(Date lastRegistryDate) {
        this.lastRegistryDate = lastRegistryDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.User", cascade=CascadeType.ALL)
    public Set<SoldBooks> getSoldBooks() {
        return this.soldBooks;
    }

    public void setSoldBooks(Set<SoldBooks> soldBooks) {
        this.soldBooks = soldBooks;
    }

}