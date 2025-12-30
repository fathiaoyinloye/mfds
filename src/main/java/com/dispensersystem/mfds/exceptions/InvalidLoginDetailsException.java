package com.dispensersystem.mfds.exceptions;

public class InvalidLoginDetailsException extends MultiFuelDispenserServiceException {
    public InvalidLoginDetailsException() {
        super("Invalid Password");
    }
}
