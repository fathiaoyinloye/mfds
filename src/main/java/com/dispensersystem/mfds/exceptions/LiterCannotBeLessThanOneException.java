package com.dispensersystem.mfds.exceptions;

public class LiterCannotBeLessThanOneException extends MultiFuelDispenserServiceException {
    public LiterCannotBeLessThanOneException(){
        super("Liters To Be Bought cannot be Less Than Zero");
    }
}
