package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.ChildAlertDTO;
import com.openclassrooms.SafetyNetAlerts.dto.HouseholdMemberDTO;
import com.openclassrooms.SafetyNetAlerts.mapper.ChildAlertMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for child alert functionality.
 * Retrieves children living at a given address along with their household members.
 */
@Service
public class ChildAlertService {

    private final PersonLookupService lookupService;

    /**
     * Constructs a ChildAlertService with the given lookup service.
     *
     * @param lookupService service used to retrieve persons with medical data
     */
    public ChildAlertService(PersonLookupService lookupService) {
        this.lookupService = lookupService;
    }

    /**
     * Retrieves all children living at the given address.
     * Each child is returned with their age and list of household members.
     *
     * @param address address to search
     * @return list of child alert DTOs
     */
    public List<ChildAlertDTO> getChildrenByAddress(String address) {

        List<PersonMedicalInfo> people =
                lookupService.findByAddress(address);

        return people.stream()
                .filter(info -> info.getAge() <= 18)
                .map(child -> {

                    List<HouseholdMemberDTO> household =
                            people.stream()
                                    .filter(p ->
                                            !(p.getPerson().getFirstName().equals(child.getPerson().getFirstName()) &&
                                                    p.getPerson().getLastName().equals(child.getPerson().getLastName()))
                                    )
                                    .map(p -> ChildAlertMapper.toHouseholdMember(p.getPerson()))
                                    .toList();

                    return ChildAlertMapper.toDto(
                            child.getPerson(),
                            child.getAge(),
                            household
                    );
                })
                .toList();
    }

}
