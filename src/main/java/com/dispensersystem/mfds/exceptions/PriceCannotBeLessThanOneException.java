package com.dispensersystem.mfds.exceptions;

public class PriceCannotBeLessThanOneException extends MultiFuelDispenserServiceException {
    public PriceCannotBeLessThanOneException() {
        super("Price Cannot be Less Than One");
    }
}
