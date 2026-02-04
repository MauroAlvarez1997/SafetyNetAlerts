package com.openclassrooms.SafetyNetAlerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.dto.MedicalRecordDTO;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicalRecordController.class)
@DisplayName("MedicalRecordController Tests")
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /medicalRecord should return all medical records")
    void getAll_shouldReturnMedicalRecords() throws Exception {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setFirstName("John");
        dto.setMedications(List.of("aznol:350mg"));

        when(medicalRecordService.getAllMedicalRecords()).thenReturn(List.of(dto));

        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].medications[0]").value("aznol:350mg"));
    }

    @Test
    @DisplayName("POST /medicalRecord should create and return new medical record")
    void create_shouldReturnOk() throws Exception {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("New");
        record.setLastName("Record");

        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setFirstName("New");
        dto.setLastName("Record");

        when(medicalRecordService.addMedicalRecord(any(MedicalRecord.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("New"))
                .andExpect(jsonPath("$.lastName").value("Record"));
    }

    @Test
    @DisplayName("PUT /medicalRecord/{firstName}/{lastName} should update and return medical record")
    void update_shouldReturnUpdatedMedicalRecord() throws Exception {
        MedicalRecord updateRequest = new MedicalRecord();
        updateRequest.setBirthdate("03/06/1984");
        updateRequest.setMedications(List.of("aznol:350mg"));
        updateRequest.setAllergies(List.of("nillacilan"));

        MedicalRecordDTO updatedDto = new MedicalRecordDTO();
        updatedDto.setFirstName("John");
        updatedDto.setLastName("Boyd");
        updatedDto.setBirthdate("03/06/1984");
        updatedDto.setMedications(List.of("aznol:350mg"));

        when(medicalRecordService.updateMedicalRecord(eq("John"), eq("Boyd"), any(MedicalRecord.class)))
                .thenReturn(updatedDto);

        mockMvc.perform(put("/medicalRecord/{firstName}/{lastName}", "John", "Boyd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.birthdate").value("03/06/1984"))
                .andExpect(jsonPath("$.medications[0]").value("aznol:350mg"));
    }

    @Test
    @DisplayName("DELETE /medicalRecord/{firstName}/{lastName} should delete medical record and return OK")
    void delete_shouldReturnOk() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord("John", "Boyd");

        mockMvc.perform(delete("/medicalRecord/{firstName}/{lastName}", "John", "Boyd"))
                .andExpect(status().isOk());

        verify(medicalRecordService, times(1)).deleteMedicalRecord("John", "Boyd");
    }
}