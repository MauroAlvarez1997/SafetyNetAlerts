package com.openclassrooms.SafetyNetAlerts.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * Catches specific exceptions and maps them to appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles {@link PersonNotFoundException} thrown in the application.
     *
     * @param ex the exception
     * @return ResponseEntity with HTTP 404 status and the exception message
     */
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> handlePersonNotFound(PersonNotFoundException ex) {
        logger.error("Person not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles {@link FireStationNotFoundException} thrown in the application.
     *
     * @param ex the exception
     * @return ResponseEntity with HTTP 404 status and the exception message
     */
    @ExceptionHandler(FireStationNotFoundException.class)
    public ResponseEntity<String> handleFireStationNotFound(FireStationNotFoundException ex) {
        logger.error("Fire Station not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles {@link MedicalRecordNotFoundException} thrown in the application.
     *
     * @param ex the exception
     * @return ResponseEntity with HTTP 404 status and the exception message
     */
    @ExceptionHandler(MedicalRecordNotFoundException.class)
    public ResponseEntity<String> handleMedicalRecordNotFound(MedicalRecordNotFoundException ex) {
        logger.error("Medical Record not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles all other uncaught exceptions.
     *
     * @param ex the exception
     * @return ResponseEntity with HTTP 500 status and a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOther(Exception ex) {
        logger.error("Unexpected error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
    }
}
