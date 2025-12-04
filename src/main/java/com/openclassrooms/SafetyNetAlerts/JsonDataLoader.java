package com.openclassrooms.SafetyNetAlerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsonDataLoader {

    private final List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("data.json");
        if (is == null) {
            throw new IllegalStateException("data.json not found in classpath");
        }

        // Read root as a Map and then map each person entry to Person class
        Map<String, Object> root = mapper.readValue(is, Map.class);
        List<Map<String, Object>> personsList = (List<Map<String, Object>>) root.get("persons");
        if (personsList != null) {
            for (Map<String, Object> p : personsList) {
                Person person = mapper.convertValue(p, Person.class);
                persons.add(person);
            }
        }
    }
}