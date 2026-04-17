package com.bootcamp.dto;

import com.bootcamp.javax.validation.constraints.Size;

public class ApplicantCreateRequest extends UserCreateRequest {
    @Size(max = 1000, message = "About section cannot exceed 1000 characters")
    private String about;
    
    // Constructors
    public ApplicantCreateRequest() {}
    
    public ApplicantCreateRequest(String firstName, String lastName, java.time.LocalDate dateOfBirth, 
                                String nationalityIdentity, String email, String password, String about) {
        super(firstName, lastName, dateOfBirth, nationalityIdentity, email, password);
        this.about = about;
    }
    
    // Getters and Setters
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
} 


