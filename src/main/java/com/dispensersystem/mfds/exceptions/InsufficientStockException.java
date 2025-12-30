package com.dispensersystem.mfds.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super("Available Liters Is Insufficient For The Quantity To Be Bought");
    }
}
