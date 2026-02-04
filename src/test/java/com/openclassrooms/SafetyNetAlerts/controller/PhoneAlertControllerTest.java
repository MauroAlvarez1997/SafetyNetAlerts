package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.service.FireStationService;
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

@WebMvcTest(PhoneAlertController.class)
@DisplayName("PhoneAlertController Tests")
class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @Test
    @DisplayName("GET /phoneAlert should return list of phone numbers for given fire station")
    void getPhones_returnsPhoneNumbers() throws Exception {
        // Arrange
        when(fireStationService.getPhoneNumbersByStation("1"))
                .thenReturn(List.of(
                        "841-874-6512",
                        "841-874-8547",
                        "841-874-7462"
                ));

        // Act & Assert
        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("841-874-6512"))
                .andExpect(jsonPath("$[1]").value("841-874-8547"))
                .andExpect(jsonPath("$[2]").value("841-874-7462"));
    }

    @Test
    @DisplayName("GET /phoneAlert should return empty list when no phones found for fire station")
    void getPhones_returnsEmptyList_whenNoPhonesFound() throws Exception {
        // Arrange
        when(fireStationService.getPhoneNumbersByStation("99"))
                .thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "99"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
