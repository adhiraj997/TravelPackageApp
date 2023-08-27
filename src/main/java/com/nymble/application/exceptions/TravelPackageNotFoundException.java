package com.nymble.application.exceptions;

public class TravelPackageNotFoundException extends RuntimeException {
    public TravelPackageNotFoundException(String message) {
        super(message);
    }
}
