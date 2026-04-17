package com.bootcamp.dto;

public class InstructorResponse extends UserResponse {
    private String companyName;
    
    // Constructors
    public InstructorResponse() {}
    
    public InstructorResponse(Long id, String firstName, String lastName, java.time.LocalDate dateOfBirth, 
                            String nationalityIdentity, String email, String companyName) {
        super(id, firstName, lastName, dateOfBirth, nationalityIdentity, email);
        this.companyName = companyName;
    }
    
    // Getters and Setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
} 




