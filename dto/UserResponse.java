package com.bootcamp.dto;

import java.time.LocalDate;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationalityIdentity;
    private String email;
    
    // Constructors
    public UserResponse() {}
    
    public UserResponse(Long id, String firstName, String lastName, LocalDate dateOfBirth, 
                       String nationalityIdentity, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationalityIdentity = nationalityIdentity;
        this.email = email;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getNationalityIdentity() { return nationalityIdentity; }
    public void setNationalityIdentity(String nationalityIdentity) { this.nationalityIdentity = nationalityIdentity; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
} 


