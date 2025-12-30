package com.dispensersystem.mfds.exceptions;

public class FuelDoesNotExistException extends MultiFuelDispenserServiceException {
    public FuelDoesNotExistException() {
        super("Fuel Does Not Exist");
    }
}
