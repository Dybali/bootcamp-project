package com.bootcamp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeCreateRequest extends UserCreateRequest {
    @NotBlank(message = "Position is required")
    @Size(min = 2, max = 100, message = "Position must be between 2 and 100 characters")
    private String position;
    
    // Constructors
    public EmployeeCreateRequest() {}
    
    public EmployeeCreateRequest(String firstName, String lastName, java.time.LocalDate dateOfBirth, 
                               String nationalityIdentity, String email, String password, String position) {
        super(firstName, lastName, dateOfBirth, nationalityIdentity, email, password);
        this.position = position;
    }
    
    // Getters and Setters
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
} 




