package com.dispensersystem.mfds.exceptions;

public class LiterCannotBeLessThanZeroException extends MultiFuelDispenserServiceException {
    public LiterCannotBeLessThanZeroException(){
        super("Liters To Be Bought cannot be Less Than Zero");
    }
}
