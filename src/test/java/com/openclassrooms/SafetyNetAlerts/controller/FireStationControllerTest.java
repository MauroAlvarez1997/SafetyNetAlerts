package com.openclassrooms.SafetyNetAlerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.dto.FireStationDTO;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.service.FireStationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FireStationController.class)
@DisplayName("FireStationController Tests")
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /firestation should return all fire stations")
    void getAll_shouldReturnFireStations() throws Exception {
        FireStationDTO dto = new FireStationDTO();
        dto.setAddress("1509 Culver St");
        dto.setStation("3");

        when(fireStationService.getAllFireStations())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"))
                .andExpect(jsonPath("$[0].station").value("3"));
    }

    @Test
    @DisplayName("POST /firestation should create and return new fire station")
    void create_shouldReturnCreatedFireStation() throws Exception {
        FireStation input = new FireStation();
        input.setAddress("29 15th St");
        input.setStation("2");

        FireStationDTO dto = new FireStationDTO();
        dto.setAddress("29 15th St");
        dto.setStation("2");

        when(fireStationService.addFireStation(any(FireStation.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("29 15th St"))
                .andExpect(jsonPath("$.station").value("2"));
    }

    @Test
    @DisplayName("PUT /firestation/{address} should update and return fire station")
    void update_shouldReturnUpdatedFireStation() throws Exception {
        FireStation update = new FireStation();
        update.setStation("4");

        FireStationDTO dto = new FireStationDTO();
        dto.setAddress("1509 Culver St");
        dto.setStation("4");

        when(fireStationService.updateFireStation(eq("1509 Culver St"), any(FireStation.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/firestation/{address}", "1509 Culver St")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.station").value("4"));
    }

    @Test
    @DisplayName("DELETE /firestation/{address} should delete fire station and return OK")
    void delete_shouldReturnOk() throws Exception {
        doNothing().when(fireStationService).deleteFireStation("1509 Culver St");

        mockMvc.perform(delete("/firestation/{address}", "1509 Culver St"))
                .andExpect(status().isOk());

        verify(fireStationService, times(1))
                .deleteFireStation("1509 Culver St");
    }
}
