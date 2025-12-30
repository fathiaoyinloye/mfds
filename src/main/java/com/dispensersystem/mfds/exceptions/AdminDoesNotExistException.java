package com.dispensersystem.mfds.exceptions;

public class AdminDoesNotExistException extends MultiFuelDispenserServiceException {
    public AdminDoesNotExistException() {
        super("Admin Does Not Exist");
    }
}
