package com.bootcamp.dto;

public class EmployeeResponse extends UserResponse {
    private String position;
    
    // Constructors
    public EmployeeResponse() {}
    
    public EmployeeResponse(Long id, String firstName, String lastName, java.time.LocalDate dateOfBirth, 
                          String nationalityIdentity, String email, String position) {
        super(id, firstName, lastName, dateOfBirth, nationalityIdentity, email);
        this.position = position;
    }
    
    // Getters and Setters
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
} 


