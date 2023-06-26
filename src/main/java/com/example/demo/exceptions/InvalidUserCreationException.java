package com.example.demo.exceptions;

public class InvalidUserCreationException extends RuntimeException {
    public InvalidUserCreationException(String s) {
        super(s);
    }
}
