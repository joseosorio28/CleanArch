package com.pragma.exceptions;

public class ClientSearchAgeException extends RuntimeException{

    public ClientSearchAgeException() {
        super("Request parameter Age, must be between 1 and 100");
    }
}
