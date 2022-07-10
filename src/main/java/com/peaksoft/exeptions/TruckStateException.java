package com.peaksoft.exeptions;

public class TruckStateException extends RuntimeException{
    public TruckStateException() {
        super();
    }

    public TruckStateException(String message) {
        super(message);
    }
}
