package com.dispensersystem.mfds.exceptions;

public class AdminAlreadyExitException extends MultiFuelDispenserServiceException {
    public AdminAlreadyExitException() {
        super("Admin Already Exist");
    }
}
