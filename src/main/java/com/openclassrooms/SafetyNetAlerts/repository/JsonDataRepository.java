package com.openclassrooms.SafetyNetAlerts.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Repository
public class JsonDataRepository {

    private final JsonData data;

    public JsonDataRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/data.json");

        data = mapper.readValue(file, JsonData.class);
    }

    public JsonData getData() {
        return data;
    }
}
