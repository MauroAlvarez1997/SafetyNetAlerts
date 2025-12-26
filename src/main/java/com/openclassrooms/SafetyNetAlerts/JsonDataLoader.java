package com.openclassrooms.SafetyNetAlerts;


import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.util.List;

@Component
public class JsonDataLoader {

    private final ObjectMapper mapper;
    private JsonData data;

    public JsonDataLoader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void loadData() {
        try {
            File file = new File("src/main/resources/data.json");
            this.data = mapper.readValue(file, JsonData.class);

            System.out.println("JSON data loaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JSON data");
        }
    }

    public List<Person> getPersons() { return data.getPersons(); }
    public List<FireStation> getFireStations() { return data.getFireStations(); }
    public List<MedicalRecord> getMedicalRecords() { return data.getMedicalRecords(); }
}