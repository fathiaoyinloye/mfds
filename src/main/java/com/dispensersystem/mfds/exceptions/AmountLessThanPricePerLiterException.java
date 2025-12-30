package com.dispensersystem.mfds.exceptions;

public class AmountLessThanPricePerLiterException extends MultiFuelDispenserServiceException {
    public AmountLessThanPricePerLiterException() {
        super("Amount cannot be less than Price Per Liter");
    }
}
