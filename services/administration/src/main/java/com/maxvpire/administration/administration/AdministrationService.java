package com.maxvpire.administration.administration;

import com.maxvpire.administration.administration.dto.*;
import com.maxvpire.administration.administration.events.AdministrationCreatedEvent;
import com.maxvpire.administration.administration.events.AdministrationDeletedEvent;
import com.maxvpire.administration.administration.events.EventService;
import com.maxvpire.administration.exception.AdministrationNotFoundException;
import com.maxvpire.administration.exception.NotValidGenderTypeException;
import com.maxvpire.administration.exception.RepeatedActionException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministrationService {
    private final AdministrationRepository administrationRepository;
    private final AdministrationMapper administrationMapper;
    private final EventService eventService;

    public List<AdministrationResponse> findAll() {
        return administrationRepository.findAll()
                .stream()
                .map(administrationMapper::toAdministrationResponse)
                .collect(Collectors.toList());
    }

    public String create(@Valid AdministrationRequest request) {
        Administration administration = Administration.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .phone(request.phone())
                .gender(request.gender())
                .dateOfBirth(request.dateOfBirth())
                .build();
        Administration data = administrationRepository.save(administration);

        AdministrationCreatedEvent event = AdministrationCreatedEvent.builder()
                .id(data.getId())
                .phone(data.getPhone())
                .build();
        eventService.sendEvent(event);
        return data.getId();
    }


    public AdministrationResponse findById(String id) {
        return administrationRepository.findById(id)
                .map(administrationMapper::toAdministrationResponse)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));
    }

    public List<AdministrationResponse> searchByName(String name) {
        return administrationRepository.searchByFirstnameAndLastname(name)
                .stream()
                .map(administrationMapper::toAdministrationResponse)
                .collect(Collectors.toList());
    }

    public List<AdministrationResponse> searchByPhone(String phone) {
        return administrationRepository.searchByPhone(phone)
                .stream()
                .map(administrationMapper::toAdministrationResponse)
                .collect(Collectors.toList());
    }

    public AdministrationResponse findByPhoneNumber(String phone) {
        return administrationRepository.findAdministrationsByPhone(phone)
                .map(administrationMapper::toAdministrationResponse)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));
    }

    public List<AdministrationResponse> getByGender(String gender) {
        if (gender.equals("MALE") || gender.equals("FEMALE")) {
            Gender g = gender.equals("MALE") ? Gender.MALE : Gender.FEMALE;
            return administrationRepository.findAdministrationsByGender(g)
                    .stream()
                    .map(administrationMapper::toAdministrationResponse)
                    .collect(Collectors.toList());
        }else{
            throw new NotValidGenderTypeException("Gender type not supported");
        }
    }


    public AdministrationActiveResponse isActive(String id) {
        return administrationRepository.findById(id)
                .map(administrationMapper::toAdministrationActiveResponse)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration is not found!"));
    }

    public List<AdministrationResponse> getActiveAdministrations() {
        return administrationRepository.findAdministrationsByActive(true)
                .stream()
                .map(administrationMapper::toAdministrationResponse)
                .collect(Collectors.toList());
    }

    public List<AdministrationResponse> getInactiveAdministrations() {
        return administrationRepository.findAdministrationsByActive(false)
                .stream()
                .map(administrationMapper::toAdministrationResponse)
                .collect(Collectors.toList());
    }

    public AdministrationDeletedResponse isDeleted(String id) {
        return administrationRepository.findById(id)
                .map(administrationMapper::toAdministrationDeletedResponse)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));
    }

    public void inActive(String id) {
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));

        if(administration.isActive()){
            administration.setActive(false);
            administrationRepository.save(administration);
            AdministrationDeletedEvent event = AdministrationDeletedEvent.builder()
                    .id(administration.getId())
                    .build();
            eventService.sendEvent(event);
        }
        else {
            throw new RepeatedActionException("You can't inactive this administration because this administration have inactivated away!");
        }
    }

    public void active(String id) {
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));

        if(!administration.isActive()){
            administration.setActive(true);
            administrationRepository.save(administration);
            AdministrationCreatedEvent event = AdministrationCreatedEvent.builder()
                    .id(administration.getId())
                    .phone(administration.getPhone())
                    .build();
            eventService.sendEvent(event);
        }
        else{
            throw new RepeatedActionException("You can't activate active administration!");
        }
    }

    public void deleteAdministration(String id) {
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));

        if(!administration.isDeleted()){
            administration.setDeleted(true);
            administrationRepository.save(administration);
            AdministrationDeletedEvent event = AdministrationDeletedEvent.builder()
                    .id(administration.getId())
                    .build();
            eventService.sendEvent(event);
        }
        else {
            throw new RepeatedActionException("You can't delete deleted administration!");
        }
    }

    public void restoreAdministration(String id) {
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));

        if(administration.isDeleted()){
           administration.setDeleted(false);
           administrationRepository.save(administration);
            AdministrationCreatedEvent event = AdministrationCreatedEvent.builder()
                    .id(administration.getId())
                    .phone(administration.getPhone())
                    .build();
            eventService.sendEvent(event);
        }

        else{
            throw new RepeatedActionException("You can't restore not deleted administration!");
        }
    }


    public void update(String id, AdministrationUpdateRequest request) {
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));

        mergeAdministration(administration, request);
        administrationRepository.save(administration);
    }

    private void mergeAdministration(Administration administration, AdministrationUpdateRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            administration.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            administration.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.phone())){
            administration.setPhone(request.phone());
        }
        if(request.dateOfBirth() != null){
            administration.setDateOfBirth(request.dateOfBirth());
        }
    }

    public void uploadAvatar(String id, String uuidFilename) {
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(() -> new AdministrationNotFoundException("Administration not found!"));

        administration.setAvatar(uuidFilename);
        administrationRepository.save(administration);
    }
}
