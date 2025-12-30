package com.dispensersystem.mfds.exceptions;

public class LiterOrQuantityCannotBeLessThanOneException extends MultiFuelDispenserServiceException {
    public LiterOrQuantityCannotBeLessThanOneException() {
        super("Liter Or Quantity Cannot Be Less Than One");
    }
}
