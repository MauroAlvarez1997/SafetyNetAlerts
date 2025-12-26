package com.openclassrooms.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class JsonDataRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/data.json");
    private JsonData data;

    @PostConstruct
    public void init() {
        try {
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
                    .writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to persist data.json", e);
        }
    }
}

