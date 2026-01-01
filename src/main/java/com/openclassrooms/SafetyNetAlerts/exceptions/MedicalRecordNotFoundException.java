package com.openclassrooms.SafetyNetAlerts.exceptions;

/**
 * Exception thrown when a MedicalRecord is not found in the system.
 */
public class MedicalRecordNotFoundException extends RuntimeException {

    /**
     * Constructs a new MedicalRecordNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining why the exception occurred
     */
    public MedicalRecordNotFoundException(String message) {
        super(message);
    }
}
