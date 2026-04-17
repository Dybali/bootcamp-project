package com.bootcamp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class RegisterRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Nationality identity is required")
    @Size(min = 11, max = 11, message = "Nationality identity must be 11 characters")
    private String nationalityIdentity;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    // Constructors
    public RegisterRequest() {}
    
    public RegisterRequest(
            String firstName,
            String lastName,
            String email,
            LocalDate dateOfBirth,
            String nationalityIdentity,
            String password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.nationalityIdentity = nationalityIdentity;
        this.password = password;
    }
    
    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getNationalityIdentity() { return nationalityIdentity; }
    public void setNationalityIdentity(String nationalityIdentity) { this.nationalityIdentity = nationalityIdentity; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
} 




