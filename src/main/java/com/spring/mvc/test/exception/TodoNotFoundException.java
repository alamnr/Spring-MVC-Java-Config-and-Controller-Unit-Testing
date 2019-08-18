package com.spring.mvc.test.exception;

public class TodoNotFoundException extends  Exception {

    public TodoNotFoundException(String message) {
        super(message);
    }

}
