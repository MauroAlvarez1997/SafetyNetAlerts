package com.openclassrooms.SafetyNetAlerts.repository;

import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDataRepositoryTest {

    private JsonDataRepository repository;
    private File tempFile;

    @BeforeEach
    void setUp() throws Exception {
        // Create temp JSON file
        tempFile = File.createTempFile("data", ".json");
        tempFile.deleteOnExit();

        String json = """
            {
              "persons": [],
              "firestations": [],
              "medicalrecords": []
            }
            """;

        Files.writeString(tempFile.toPath(), json);

        repository = new JsonDataRepository();

        // Replace the private 'file' field via reflection
        Field fileField = JsonDataRepository.class.getDeclaredField("file");
        fileField.setAccessible(true);
        fileField.set(repository, tempFile);
    }

    @Test
    void init_shouldLoadJsonData() {
        repository.init();

        JsonData data = repository.getData();

        assertNotNull(data);
        assertNotNull(data.getPersons());
        assertNotNull(data.getFireStations());
        assertNotNull(data.getMedicalRecords());
    }

    @Test
    void getData_shouldReturnLoadedData() {
        repository.init();

        JsonData data = repository.getData();

        assertNotNull(data);
    }

    @Test
    void persist_shouldWriteDataWithoutThrowing() {
        repository.init();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        repository.getData().getPersons().add(person);

        assertDoesNotThrow(() -> repository.persist());
    }
}
