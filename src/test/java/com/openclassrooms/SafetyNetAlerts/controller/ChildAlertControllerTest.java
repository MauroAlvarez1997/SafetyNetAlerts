package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.dto.ChildAlertDTO;
import com.openclassrooms.SafetyNetAlerts.dto.HouseholdMemberDTO;
import com.openclassrooms.SafetyNetAlerts.service.ChildAlertService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChildAlertController.class)
@DisplayName("ChildAlertController Tests")
class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChildAlertService childAlertService;

    @Test
    @DisplayName("GET /childAlert should return list of children with household members when address has children")
    void getChildrenByAddress_returnsChildren() throws Exception {
        // Arrange
        List<HouseholdMemberDTO> household = List.of(
                new HouseholdMemberDTO("John", "Boyd")
        );

        ChildAlertDTO child = new ChildAlertDTO(
                "Jane",
                "Boyd",
                10,
                household
        );

        when(childAlertService.getChildrenByAddress("1509 Culver St"))
                .thenReturn(List.of(child));

        // Act & Assert
        mockMvc.perform(get("/childAlert")
                        .param("address", "1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jane"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].age").value(10))
                .andExpect(jsonPath("$[0].householdMembers[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].householdMembers[0].lastName").value("Boyd"));
    }

    @Test
    @DisplayName("GET /childAlert should return empty list when no children found at address")
    void getChildrenByAddress_returnsEmptyList_whenNoChildren() throws Exception {
        // Arrange
        when(childAlertService.getChildrenByAddress("Unknown Address"))
                .thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/childAlert")
                        .param("address", "Unknown Address"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
