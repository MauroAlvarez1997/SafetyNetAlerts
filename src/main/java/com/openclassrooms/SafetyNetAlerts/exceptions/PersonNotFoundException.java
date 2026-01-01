package com.openclassrooms.SafetyNetAlerts.exceptions;

/**
 * Exception thrown when a Person is not found in the system.
 */
public class PersonNotFoundException extends RuntimeException {

    /**
     * Constructs a new PersonNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining why the exception occurred
     */
    public PersonNotFoundException(String message) {
        super(message);
    }
}
