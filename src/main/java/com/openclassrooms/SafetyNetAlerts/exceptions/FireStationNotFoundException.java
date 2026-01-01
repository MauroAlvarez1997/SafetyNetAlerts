package com.openclassrooms.SafetyNetAlerts.exceptions;

/**
 * Exception thrown when a fire station cannot be found.
 */
public class FireStationNotFoundException extends RuntimeException {

    /**
     * Constructs a new FireStationNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public FireStationNotFoundException(String message) {
        super(message);
    }
}
