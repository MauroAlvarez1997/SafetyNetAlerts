package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.ChildAlertDTO;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("ChildAlertService Tests")
class ChildAlertServiceTest {

    @Mock
    private PersonLookupService lookupService;

    @InjectMocks
    private ChildAlertService childAlertService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return children with household members when address has both children and adults")
    void getChildrenByAddress_returnsChildrenWithHouseholdMembers() {
        // Arrange
        Person child = new Person();
        child.setFirstName("John");
        child.setLastName("Doe");

        Person adult = new Person();
        adult.setFirstName("Jane");
        adult.setLastName("Doe");

        MedicalRecord record = new MedicalRecord();

        PersonMedicalInfo childInfo =
                new PersonMedicalInfo(child, 10, record);

        PersonMedicalInfo adultInfo =
                new PersonMedicalInfo(adult, 35, record);

        when(lookupService.findByAddress("123 Main St"))
                .thenReturn(List.of(childInfo, adultInfo));

        // Act
        List<ChildAlertDTO> result =
                childAlertService.getChildrenByAddress("123 Main St");

        // Assert
        assertThat(result).hasSize(1);

        ChildAlertDTO dto = result.get(0);
        assertThat(dto.getFirstName()).isEqualTo("John");
        assertThat(dto.getLastName()).isEqualTo("Doe");
        assertThat(dto.getAge()).isEqualTo(10);

        assertThat(dto.getHouseholdMembers()).hasSize(1);
        assertThat(dto.getHouseholdMembers().get(0).getFirstName())
                .isEqualTo("Jane");
    }

    @Test
    @DisplayName("Should return empty list when address has no children (only adults)")
    void getChildrenByAddress_returnsEmptyList_whenNoChildren() {
        // Arrange
        Person adult = new Person();
        adult.setFirstName("Jane");
        adult.setLastName("Doe");

        MedicalRecord record = new MedicalRecord();

        PersonMedicalInfo adultInfo =
                new PersonMedicalInfo(adult, 40, record);

        when(lookupService.findByAddress("123 Main St"))
                .thenReturn(List.of(adultInfo));

        // Act
        List<ChildAlertDTO> result =
                childAlertService.getChildrenByAddress("123 Main St");

        // Assert
        assertThat(result).isEmpty();
    }
}
