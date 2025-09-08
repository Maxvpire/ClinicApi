package com.maxvpire.administration.administration;

import com.maxvpire.administration.administration.dto.AdministrationActiveResponse;
import com.maxvpire.administration.administration.dto.AdministrationDeletedResponse;
import com.maxvpire.administration.administration.dto.AdministrationResponse;
import org.springframework.stereotype.Service;

@Service
public class AdministrationMapper {
    public AdministrationResponse toAdministrationResponse(Administration administration) {
        return new  AdministrationResponse(
                administration.getId(),
                administration.getFirstname(),
                administration.getLastname(),
                administration.getPhone(),
                administration.getAvatar(),
                administration.getGender(),
                administration.isActive(),
                administration.isDeleted(),
                administration.getDateOfBirth(),
                administration.getCreatedAt(),
                administration.getUpdatedAt()
        );
    }

    public AdministrationActiveResponse toAdministrationActiveResponse(Administration administration) {
        return new AdministrationActiveResponse(
                administration.isActive()
        );
    }

    public AdministrationDeletedResponse toAdministrationDeletedResponse(Administration administration) {
        return new AdministrationDeletedResponse(
                administration.isDeleted()
        );
    }
}