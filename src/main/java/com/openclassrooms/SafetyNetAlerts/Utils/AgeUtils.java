package com.openclassrooms.SafetyNetAlerts.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for calculating a person's age from their birthdate.
 * <p>
 * Provides a single static method to calculate age in years based on a birthdate string
 * in "MM/dd/yyyy" format.
 */
public class AgeUtils {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // Private constructor to prevent instantiation
    private AgeUtils() {}

    /**
     * Calculates the age in years from a birthdate string.
     *
     * @param birthdate the birthdate as a String in "MM/dd/yyyy" format
     * @return the calculated age in years
     * @throws java.time.format.DateTimeParseException if the birthdate is invalid
     */
    public static int calculateAge(String birthdate) {
        LocalDate birth = LocalDate.parse(birthdate, FORMATTER);
        return Period.between(birth, LocalDate.now()).getYears();
    }
}
