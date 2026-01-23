package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.MedicalRecordRepository;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PersonLookupServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private PersonLookupService personLookupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByAddress_returnsPersonMedicalInfo_whenMedicalRecordExists() {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("123 Main St");

        MedicalRecord record = new MedicalRecord();
        record.setBirthdate("01/01/2000");

        when(personRepository.findByAddress("123 Main St"))
                .thenReturn(List.of(person));

        when(medicalRecordRepository.findByName("John", "Doe"))
                .thenReturn(Optional.of(record));

        // Act
        List<PersonMedicalInfo> result =
                personLookupService.findByAddress("123 Main St");

        // Assert
        assertThat(result).hasSize(1);

        PersonMedicalInfo info = result.get(0);
        assertThat(info.getPerson()).isEqualTo(person);
        assertThat(info.getMedicalRecord()).isEqualTo(record);
        assertThat(info.getAge()).isGreaterThan(0);
    }

    @Test
    void findByAddress_filtersOutPerson_whenMedicalRecordMissing() {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        when(personRepository.findByAddress("123 Main St"))
                .thenReturn(List.of(person));

        when(medicalRecordRepository.findByName("John", "Doe"))
                .thenReturn(Optional.empty());

        // Act
        List<PersonMedicalInfo> result =
                personLookupService.findByAddress("123 Main St");

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    void findByAddresses_returnsCombinedResults_forMultipleAddresses() {
        // Arrange
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Smith");

        MedicalRecord record1 = new MedicalRecord();
        record1.setBirthdate("01/01/2000");

        MedicalRecord record2 = new MedicalRecord();
        record2.setBirthdate("01/01/1990");

        when(personRepository.findByAddress("Addr1"))
                .thenReturn(List.of(person1));

        when(personRepository.findByAddress("Addr2"))
                .thenReturn(List.of(person2));

        when(medicalRecordRepository.findByName("John", "Doe"))
                .thenReturn(Optional.of(record1));

        when(medicalRecordRepository.findByName("Jane", "Smith"))
                .thenReturn(Optional.of(record2));

        // Act
        List<PersonMedicalInfo> result =
                personLookupService.findByAddresses(List.of("Addr1", "Addr2"));

        // Assert
        assertThat(result).hasSize(2);
    }
}
