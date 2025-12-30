package com.dispensersystem.mfds.exceptions;

public class FuelAlreadyExistException extends MultiFuelDispenserServiceException{
    public FuelAlreadyExistException() {
        super("Fuel Already Exist");
    }
}
