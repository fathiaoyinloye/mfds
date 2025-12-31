package com.dispensersystem.mfds.exceptions;

public class FuelAttendantAlreadyExistException extends RuntimeException {
    public FuelAttendantAlreadyExistException() {
        super("Fuel Attendant Already Exist");
    }
}
