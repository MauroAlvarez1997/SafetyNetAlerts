package com.openclassrooms.SafetyNetAlerts.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PersonInfoDTO class.
 * Tests constructor, getters, setters, and edge cases.
 */
class PersonInfoDTOTest {

    private PersonInfoDTO personInfoDTO;
    private List<String> medications;
    private List<String> allergies;

    @BeforeEach
    void setUp() {
        medications = new ArrayList<>(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        allergies = new ArrayList<>(Arrays.asList("nillacilan", "shellfish"));

        personInfoDTO = new PersonInfoDTO(
                "John",
                "Boyd",
                "1509 Culver St",
                38,
                "jaboyd@email.com",
                medications,
                allergies
        );
    }

    @Test
    @DisplayName("Test constructor creates object with correct values")
    void testConstructor() {
        assertNotNull(personInfoDTO);
        assertEquals("John", personInfoDTO.getFirstName());
        assertEquals("Boyd", personInfoDTO.getLastName());
        assertEquals("1509 Culver St", personInfoDTO.getAddress());
        assertEquals(38, personInfoDTO.getAge());
        assertEquals("jaboyd@email.com", personInfoDTO.getEmail());
        assertEquals(medications, personInfoDTO.getMedications());
        assertEquals(allergies, personInfoDTO.getAllergies());
    }

    @Test
    @DisplayName("Test getFirstName returns correct value")
    void testGetFirstName() {
        assertEquals("John", personInfoDTO.getFirstName());
    }

    @Test
    @DisplayName("Test setFirstName updates value correctly")
    void testSetFirstName() {
        personInfoDTO.setFirstName("Jane");
        assertEquals("Jane", personInfoDTO.getFirstName());
    }

    @Test
    @DisplayName("Test getLastName returns correct value")
    void testGetLastName() {
        assertEquals("Boyd", personInfoDTO.getLastName());
    }

    @Test
    @DisplayName("Test setLastName updates value correctly")
    void testSetLastName() {
        personInfoDTO.setLastName("Smith");
        assertEquals("Smith", personInfoDTO.getLastName());
    }

    @Test
    @DisplayName("Test getAddress returns correct value")
    void testGetAddress() {
        assertEquals("1509 Culver St", personInfoDTO.getAddress());
    }

    @Test
    @DisplayName("Test setAddress updates value correctly")
    void testSetAddress() {
        personInfoDTO.setAddress("123 Main St");
        assertEquals("123 Main St", personInfoDTO.getAddress());
    }

    @Test
    @DisplayName("Test getAge returns correct value")
    void testGetAge() {
        assertEquals(38, personInfoDTO.getAge());
    }

    @Test
    @DisplayName("Test setAge updates value correctly")
    void testSetAge() {
        personInfoDTO.setAge(40);
        assertEquals(40, personInfoDTO.getAge());
    }

    @Test
    @DisplayName("Test getEmail returns correct value")
    void testGetEmail() {
        assertEquals("jaboyd@email.com", personInfoDTO.getEmail());
    }

    @Test
    @DisplayName("Test setEmail updates value correctly")
    void testSetEmail() {
        personInfoDTO.setEmail("newemail@test.com");
        assertEquals("newemail@test.com", personInfoDTO.getEmail());
    }

    @Test
    @DisplayName("Test getMedications returns correct list")
    void testGetMedications() {
        List<String> result = personInfoDTO.getMedications();
        assertEquals(2, result.size());
        assertTrue(result.contains("aznol:350mg"));
        assertTrue(result.contains("hydrapermazol:100mg"));
    }

    @Test
    @DisplayName("Test setMedications updates list correctly")
    void testSetMedications() {
        List<String> newMedications = Arrays.asList("aspirin:100mg", "ibuprofen:200mg");
        personInfoDTO.setMedications(newMedications);

        List<String> result = personInfoDTO.getMedications();
        assertEquals(2, result.size());
        assertTrue(result.contains("aspirin:100mg"));
        assertTrue(result.contains("ibuprofen:200mg"));
    }

    @Test
    @DisplayName("Test setMedications with null list")
    void testSetMedicationsWithNull() {
        personInfoDTO.setMedications(null);
        assertNull(personInfoDTO.getMedications());
    }

    @Test
    @DisplayName("Test setMedications with empty list")
    void testSetMedicationsWithEmptyList() {
        List<String> emptyList = new ArrayList<>();
        personInfoDTO.setMedications(emptyList);

        List<String> result = personInfoDTO.getMedications();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test getAllergies returns correct list")
    void testGetAllergies() {
        List<String> result = personInfoDTO.getAllergies();
        assertEquals(2, result.size());
        assertTrue(result.contains("nillacilan"));
        assertTrue(result.contains("shellfish"));
    }

    @Test
    @DisplayName("Test setAllergies updates list correctly")
    void testSetAllergies() {
        List<String> newAllergies = Arrays.asList("peanuts", "dairy");
        personInfoDTO.setAllergies(newAllergies);

        List<String> result = personInfoDTO.getAllergies();
        assertEquals(2, result.size());
        assertTrue(result.contains("peanuts"));
        assertTrue(result.contains("dairy"));
    }

    @Test
    @DisplayName("Test setAllergies with null list")
    void testSetAllergiesWithNull() {
        personInfoDTO.setAllergies(null);
        assertNull(personInfoDTO.getAllergies());
    }

    @Test
    @DisplayName("Test setAllergies with empty list")
    void testSetAllergiesWithEmptyList() {
        List<String> emptyList = new ArrayList<>();
        personInfoDTO.setAllergies(emptyList);

        List<String> result = personInfoDTO.getAllergies();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test constructor with null medications")
    void testConstructorWithNullMedications() {
        PersonInfoDTO dto = new PersonInfoDTO(
                "Jane",
                "Doe",
                "123 Test St",
                25,
                "jane@test.com",
                null,
                allergies
        );

        assertNotNull(dto);
        assertNull(dto.getMedications());
        assertNotNull(dto.getAllergies());
    }

    @Test
    @DisplayName("Test constructor with null allergies")
    void testConstructorWithNullAllergies() {
        PersonInfoDTO dto = new PersonInfoDTO(
                "Jane",
                "Doe",
                "123 Test St",
                25,
                "jane@test.com",
                medications,
                null
        );

        assertNotNull(dto);
        assertNotNull(dto.getMedications());
        assertNull(dto.getAllergies());
    }

    @Test
    @DisplayName("Test constructor with empty lists")
    void testConstructorWithEmptyLists() {
        PersonInfoDTO dto = new PersonInfoDTO(
                "Jane",
                "Doe",
                "123 Test St",
                25,
                "jane@test.com",
                new ArrayList<>(),
                new ArrayList<>()
        );

        assertNotNull(dto);
        assertTrue(dto.getMedications().isEmpty());
        assertTrue(dto.getAllergies().isEmpty());
    }

    @Test
    @DisplayName("Test age with zero value")
    void testAgeWithZero() {
        personInfoDTO.setAge(0);
        assertEquals(0, personInfoDTO.getAge());
    }

    @Test
    @DisplayName("Test age with negative value")
    void testAgeWithNegativeValue() {
        personInfoDTO.setAge(-1);
        assertEquals(-1, personInfoDTO.getAge());
    }

    @Test
    @DisplayName("Test age with large value")
    void testAgeWithLargeValue() {
        personInfoDTO.setAge(120);
        assertEquals(120, personInfoDTO.getAge());
    }

    @Test
    @DisplayName("Test all fields can be modified")
    void testAllFieldsCanBeModified() {
        personInfoDTO.setFirstName("Modified");
        personInfoDTO.setLastName("Name");
        personInfoDTO.setAddress("Modified Address");
        personInfoDTO.setAge(50);
        personInfoDTO.setEmail("modified@email.com");

        List<String> newMeds = List.of("newmed:50mg");
        List<String> newAllergies = List.of("newallergy");
        personInfoDTO.setMedications(newMeds);
        personInfoDTO.setAllergies(newAllergies);

        assertEquals("Modified", personInfoDTO.getFirstName());
        assertEquals("Name", personInfoDTO.getLastName());
        assertEquals("Modified Address", personInfoDTO.getAddress());
        assertEquals(50, personInfoDTO.getAge());
        assertEquals("modified@email.com", personInfoDTO.getEmail());
        assertEquals(newMeds, personInfoDTO.getMedications());
        assertEquals(newAllergies, personInfoDTO.getAllergies());
    }

    @Test
    @DisplayName("Test medications list with single item")
    void testMedicationsListWithSingleItem() {
        List<String> singleMed = List.of("aspirin:100mg");
        personInfoDTO.setMedications(singleMed);

        assertEquals(1, personInfoDTO.getMedications().size());
        assertEquals("aspirin:100mg", personInfoDTO.getMedications().get(0));
    }

    @Test
    @DisplayName("Test allergies list with single item")
    void testAllergiesListWithSingleItem() {
        List<String> singleAllergy = List.of("peanuts");
        personInfoDTO.setAllergies(singleAllergy);

        assertEquals(1, personInfoDTO.getAllergies().size());
        assertEquals("peanuts", personInfoDTO.getAllergies().get(0));
    }

    @Test
    @DisplayName("Test medications list with many items")
    void testMedicationsListWithManyItems() {
        List<String> manyMeds = Arrays.asList(
                "med1:100mg", "med2:200mg", "med3:300mg",
                "med4:400mg", "med5:500mg"
        );
        personInfoDTO.setMedications(manyMeds);

        assertEquals(5, personInfoDTO.getMedications().size());
    }

    @Test
    @DisplayName("Test allergies list with many items")
    void testAllergiesListWithManyItems() {
        List<String> manyAllergies = Arrays.asList(
                "allergy1", "allergy2", "allergy3", "allergy4", "allergy5"
        );
        personInfoDTO.setAllergies(manyAllergies);

        assertEquals(5, personInfoDTO.getAllergies().size());
    }

    @Test
    @DisplayName("Test setting null first name")
    void testSetNullFirstName() {
        personInfoDTO.setFirstName(null);
        assertNull(personInfoDTO.getFirstName());
    }

    @Test
    @DisplayName("Test setting null last name")
    void testSetNullLastName() {
        personInfoDTO.setLastName(null);
        assertNull(personInfoDTO.getLastName());
    }

    @Test
    @DisplayName("Test setting null address")
    void testSetNullAddress() {
        personInfoDTO.setAddress(null);
        assertNull(personInfoDTO.getAddress());
    }

    @Test
    @DisplayName("Test setting null email")
    void testSetNullEmail() {
        personInfoDTO.setEmail(null);
        assertNull(personInfoDTO.getEmail());
    }

    @Test
    @DisplayName("Test setting empty string values")
    void testSetEmptyStringValues() {
        personInfoDTO.setFirstName("");
        personInfoDTO.setLastName("");
        personInfoDTO.setAddress("");
        personInfoDTO.setEmail("");

        assertEquals("", personInfoDTO.getFirstName());
        assertEquals("", personInfoDTO.getLastName());
        assertEquals("", personInfoDTO.getAddress());
        assertEquals("", personInfoDTO.getEmail());
    }
}
