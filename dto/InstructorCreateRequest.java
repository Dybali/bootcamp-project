package com.bootcamp.dto;

import com.bootcamp.javax.validation.constraints.NotBlank;
import com.bootcamp.javax.validation.constraints.Size;

public class InstructorCreateRequest extends UserCreateRequest {
    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;
    
    // Constructors
    public InstructorCreateRequest() {}
    
    public InstructorCreateRequest(String firstName, String lastName, java.time.LocalDate dateOfBirth, 
                                 String nationalityIdentity, String email, String password, String companyName) {
        super(firstName, lastName, dateOfBirth, nationalityIdentity, email, password);
        this.companyName = companyName;
    }
    
    // Getters and Setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
} 


