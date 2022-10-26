package com.nhnacademy.nhnmart.exception;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
