package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;


/**
 * Holds combined information about a person along with their age and medical record.
 * <p>
 * This class is used to encapsulate a {@link Person} object, their calculated age,
 * and the corresponding {@link MedicalRecord} for easier access in service logic.
 */
public class PersonMedicalInfo {

    private final Person person;
    private final int age;
    private final MedicalRecord medicalRecord;

    /**
     * Constructs a PersonMedicalInfo object.
     *
     * @param person the {@link Person} object
     * @param age the age of the person in years
     * @param medicalRecord the {@link MedicalRecord} associated with the person
     */
    public PersonMedicalInfo(Person person, int age, MedicalRecord medicalRecord) {
        this.person = person;
        this.age = age;
        this.medicalRecord = medicalRecord;
    }

    /**
     * Returns the {@link Person} object.
     *
     * @return the person
     */
    public Person getPerson() { return person; }

    /**
     * Returns the age of the person in years.
     *
     * @return the age
     */
    public int getAge() { return age; }

    /**
     * Returns the {@link MedicalRecord} associated with the person.
     *
     * @return the medical record
     */
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
}
