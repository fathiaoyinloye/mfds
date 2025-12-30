package com.dispensersystem.mfds.exceptions;

public class FuelAttendanceAlreadyExist extends MultiFuelDispenserServiceException {
    public FuelAttendanceAlreadyExist() {
        super("Two Attendants cannot have same name");
    }
}
