package com.openclassrooms.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

/**
 * Repository responsible for managing the raw JSON data persistence.
 * This class handles the initial loading of data from a JSON file into memory
 * at startup and provides a mechanism to persist changes back to the file.
 */
@Repository
public class JsonDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(JsonDataRepository.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final String filePath;
    private JsonData data;

    /**
     * Constructs the repository with a configurable file path.
     *
     * @param filePath the path to the data.json file, injected from application properties
     */
    public JsonDataRepository(@Value("${data.file.path:src/main/resources/data.json}") String filePath) {
        this.filePath = filePath;
    }

    /**
     * Initializes the data by reading the JSON file after the bean is constructed.
     * If the file is missing or unreadable, a RuntimeException is thrown to prevent
     * the application from starting in an invalid state.
     *
     * @throws RuntimeException if the JSON file cannot be loaded
     */
    @PostConstruct
    public void init() {
        logger.info("Repository: Initializing data from path: {}", filePath);
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.error("Repository: Critical error - File not found at: {}", file.getAbsolutePath());
                throw new IOException("File not found at: " + file.getAbsolutePath());
            }
            data = mapper.readValue(file, JsonData.class);
            logger.info("Repository: Data successfully loaded into memory.");
        } catch (IOException e) {
            logger.error("Repository: Failed to parse JSON data: {}", e.getMessage());
            throw new RuntimeException("Failed to load data.json", e);
        }
    }

    /**
     * Provides access to the in-memory representation of the JSON data.
     *
     * @return the current JsonData object
     */
    public JsonData getData() {
        return data;
    }

    /**
     * Synchronizes the in-memory data back to the physical JSON file.
     * This method is synchronized to prevent concurrent write issues.
     *
     * @throws RuntimeException if the data cannot be written to the file
     */
    public synchronized void persist() {
        logger.debug("Repository: Attempting to persist changes to {}", filePath);
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), data);
            logger.info("Repository: Changes successfully persisted to file.");
        } catch (IOException e) {
            logger.error("Repository: Persistence failed: {}", e.getMessage());
            throw new RuntimeException("Failed to persist data.json", e);
        }
    }
}