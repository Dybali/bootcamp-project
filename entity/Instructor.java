package com.bootcamp.entity;

import com.bootcamp.javax.persistence.*;
import com.bootcamp.javax.validation.constraints.NotBlank;
import com.bootcamp.javax.validation.constraints.Size;

@Entity
@Table(name = "instructors")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    
    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    @Column(name = "company_name", nullable = false)
    private String companyName;
    
    // Constructors
    public Instructor() {}
    
    public Instructor(String firstName, String lastName, LocalDate dateOfBirth, 
                     String nationalityIdentity, String email, String passwordHash, 
                     String passwordSalt, String companyName) {
        super(firstName, lastName, dateOfBirth, nationalityIdentity, email, passwordHash, passwordSalt);
        this.companyName = companyName;
    }
    
    // Getters and Setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
} 


