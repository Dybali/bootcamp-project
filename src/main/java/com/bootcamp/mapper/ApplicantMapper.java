package com.bootcamp.mapper;

import com.bootcamp.dto.ApplicantCreateRequest;
import com.bootcamp.dto.ApplicantResponse;
import com.bootcamp.entity.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {
    
    public Applicant toEntity(ApplicantCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        Applicant applicant = new Applicant();
        applicant.setFirstName(request.getFirstName());
        applicant.setLastName(request.getLastName());
        applicant.setDateOfBirth(request.getDateOfBirth());
        applicant.setNationalityIdentity(request.getNationalityIdentity());
        applicant.setEmail(request.getEmail());
        applicant.setAbout(request.getAbout());
        // Password will be handled in service layer
        return applicant;
    }
    
    public ApplicantResponse toResponse(Applicant applicant) {
        if (applicant == null) {
            return null;
        }
        
        return new ApplicantResponse(
            applicant.getId(),
            applicant.getFirstName(),
            applicant.getLastName(),
            applicant.getDateOfBirth(),
            applicant.getNationalityIdentity(),
            applicant.getEmail(),
            applicant.getAbout()
        );
    }
} 




