package com.spring.rest.RestwithSpring.exceptions;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message){
        super(message);
    }
}
