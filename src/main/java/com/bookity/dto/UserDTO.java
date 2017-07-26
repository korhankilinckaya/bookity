package com.bookity.dto;

import java.util.Date;

public class UserDTO extends BaseDTO {

    public String userName;

    public String userEmail;

    public String userPassword;

    public Date lastRegistryDate;

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
}
