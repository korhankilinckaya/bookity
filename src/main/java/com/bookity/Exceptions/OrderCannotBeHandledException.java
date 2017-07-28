package com.bookity.Exceptions;

public class OrderCannotBeHandledException extends Exception{
    public OrderCannotBeHandledException() {
        super("Order cannot be handled");
    }
}
