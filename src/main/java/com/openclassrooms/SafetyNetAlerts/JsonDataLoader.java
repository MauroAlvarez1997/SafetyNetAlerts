package com.openclassrooms.SafetyNetAlerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.JsonData;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class JsonDataLoader {

    private final ObjectMapper mapper;
    private JsonData data;

    // This pulls the file directly from the classpath (src/main/resources)
    @Value("classpath:data.json")
    private Resource jsonDataResource;

    public JsonDataLoader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void loadData() {
        // We use try-with-resources to ensure the stream closes automatically
        try (InputStream inputStream = jsonDataResource.getInputStream()) {
            this.data = mapper.readValue(inputStream, JsonData.class);
            System.out.println("JSON data loaded successfully from classpath!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JSON data from " + jsonDataResource.getDescription());
        }
    }

    public List<Person> getPersons() { return data.getPersons(); }
    public List<FireStation> getFireStations() { return data.getFireStations(); }
    public List<MedicalRecord> getMedicalRecords() { return data.getMedicalRecords(); }
}