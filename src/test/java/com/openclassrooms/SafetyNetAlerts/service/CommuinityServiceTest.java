package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommunityServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private CommunityService communityService;

    @Test
    void getEmailsByCity_shouldReturnDistinctNonNullEmails() {

        // ---- Mock data ----
        Person p1 = new Person();
        p1.setCity("Culver");
        p1.setEmail("john@example.com");

        Person p2 = new Person();
        p2.setCity("Culver");
        p2.setEmail("jane@example.com");

        Person p3 = new Person();
        p3.setCity("Culver");
        p3.setEmail("john@example.com"); // duplicate email

        Person p4 = new Person();
        p4.setCity("Culver");
        p4.setEmail(""); // blank email

        Person p5 = new Person();
        p5.setCity("Springfield");
        p5.setEmail("someone@springfield.com"); // different city

        when(personRepository.findAll()).thenReturn(List.of(p1, p2, p3, p4, p5));

        // ---- Execute ----
        List<String> emails = communityService.getEmailsByCity("Culver");

        // ---- Verify ----
        assertEquals(2, emails.size());
        assertTrue(emails.contains("john@example.com"));
        assertTrue(emails.contains("jane@example.com"));

        // Emails from other cities or blank should not be included
        assertFalse(emails.contains(""));
        assertFalse(emails.contains("someone@springfield.com"));

        // ---- Verify repository call ----
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void getEmailsByCity_shouldReturnEmptyList_whenNoResidentsInCity() {
        when(personRepository.findAll()).thenReturn(List.of());

        List<String> emails = communityService.getEmailsByCity("NonExistingCity");

        assertTrue(emails.isEmpty());
        verify(personRepository, times(1)).findAll();
    }
}
