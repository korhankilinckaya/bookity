package com.bookity.Exceptions;

public class UserNotLoggedInException extends Exception{
    public UserNotLoggedInException() {
        super("User is not logged in");
    }
}
