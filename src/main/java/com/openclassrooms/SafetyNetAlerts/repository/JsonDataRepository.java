package com.openclassrooms.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Repository
public class JsonDataRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String filePath; // Store the path as a String
    private JsonData data;

    // Spring injects the value from application.properties, or uses the default
    public JsonDataRepository(@Value("${data.file.path:src/main/resources/data.json}") String filePath) {
        this.filePath = filePath;
    }

    @PostConstruct
    public void init() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("File not found at: " + file.getAbsolutePath());
            }
            data = mapper.readValue(file, JsonData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data.json", e);
        }
    }

    public JsonData getData() {
        return data;
    }

    public synchronized void persist() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to persist data.json", e);
        }
    }
}