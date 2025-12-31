package com.dispensersystem.mfds.exceptions;

public class QuantityCannotBeLessThanOneException extends MultiFuelDispenserServiceException {
    public QuantityCannotBeLessThanOneException() {
        super("Quantity Cannot Be Less Than One ");
    }
}
