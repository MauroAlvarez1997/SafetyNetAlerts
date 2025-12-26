package com.openclassrooms.SafetyNetAlerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTO;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonService;
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

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_shouldReturnPersons() throws Exception {
        PersonDTO dto = new PersonDTO();
        dto.setFirstName("John");
        dto.setLastName("Boyd");
        dto.setAddress("1509 Culver St");

        when(personService.getAllPersons())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"));
    }

    @Test
    void create_shouldReturnCreatedPerson() throws Exception {
        Person input = new Person();
        input.setFirstName("Jane");
        input.setLastName("Doe");
        input.setAddress("123 Main St");

        PersonDTO dto = new PersonDTO();
        dto.setFirstName("Jane");
        dto.setLastName("Doe");
        dto.setAddress("123 Main St");

        when(personService.addPerson(any(Person.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.address").value("123 Main St"));
    }

    @Test
    void update_shouldReturnUpdatedPerson() throws Exception {
        Person update = new Person();
        update.setAddress("New Address");

        PersonDTO dto = new PersonDTO();
        dto.setFirstName("John");
        dto.setLastName("Boyd");
        dto.setAddress("New Address");

        when(personService.updatePerson(
                eq("John"),
                eq("Boyd"),
                any(Person.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/persons/{firstName}/{lastName}", "John", "Boyd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.address").value("New Address"));
    }

    @Test
    void delete_shouldReturnOk() throws Exception {
        doNothing().when(personService).deletePerson("John", "Boyd");

        mockMvc.perform(delete("/persons/{firstName}/{lastName}", "John", "Boyd"))
                .andExpect(status().isOk());

        verify(personService, times(1))
                .deletePerson("John", "Boyd");
    }
}
