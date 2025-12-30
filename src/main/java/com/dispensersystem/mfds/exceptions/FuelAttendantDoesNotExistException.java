package com.dispensersystem.mfds.exceptions;

public class FuelAttendantDoesNotExistException extends MultiFuelDispenserServiceException {
    public FuelAttendantDoesNotExistException() {
        super("Fuel Attendant Was Never Added");
    }
}
