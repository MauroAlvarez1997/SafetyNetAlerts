package com.openclassrooms.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

/**
 * Repository class that loads and persists the JSON data used by the application.
 * Provides access to all persons, fire stations, and medical records through a {@link JsonData} object.
 */
@Repository
public class JsonDataRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/data.json");
    private JsonData data;

    /**
     * Initializes the repository by reading the JSON file into memory.
     * Called automatically after the repository is constructed.
     *
     * @throws RuntimeException if the file cannot be read
     */
    @PostConstruct
    public void init() {
        try {
            data = mapper.readValue(file, JsonData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data.json", e);
        }
    }

    /**
     * Returns the in-memory JSON data object containing all application data.
     *
     * @return the {@link JsonData} object
     */
    public JsonData getData() {
        return data;
    }

    /**
     * Persists the current state of the JSON data back to the data.json file.
     * This method is synchronized to prevent concurrent writes.
     *
     * @throws RuntimeException if the file cannot be written
     */
    public synchronized void persist() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to persist data.json", e);
        }
    }
}
