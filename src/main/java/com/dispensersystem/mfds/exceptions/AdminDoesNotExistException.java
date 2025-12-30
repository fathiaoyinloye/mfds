package com.dispensersystem.mfds.exceptions;

public class AdminDoesNotExistException extends RuntimeException {
    public AdminDoesNotExistException() {
        super("Admin Does Not Exist");
    }
}
