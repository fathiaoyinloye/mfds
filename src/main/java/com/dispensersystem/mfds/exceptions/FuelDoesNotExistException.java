package com.dispensersystem.mfds.exceptions;

public class FuelDoesNotExistException extends RuntimeException {
    public FuelDoesNotExistException() {
        super("Fuel Does Not Exist");
    }
}
