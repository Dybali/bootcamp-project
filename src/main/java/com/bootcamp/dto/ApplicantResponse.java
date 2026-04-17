package com.bootcamp.dto;

public class ApplicantResponse extends UserResponse {
    private String about;
    
    // Constructors
    public ApplicantResponse() {}
    
    public ApplicantResponse(Long id, String firstName, String lastName, java.time.LocalDate dateOfBirth, 
                           String nationalityIdentity, String email, String about) {
        super(id, firstName, lastName, dateOfBirth, nationalityIdentity, email);
        this.about = about;
    }
    
    // Getters and Setters
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
} 




