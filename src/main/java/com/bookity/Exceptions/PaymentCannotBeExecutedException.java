package com.bookity.Exceptions;

public class PaymentCannotBeExecutedException extends Exception{
    public PaymentCannotBeExecutedException() {
        super("Payment cannot be executed");
    }
}
