package com.bootcamp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {
    
    @NotBlank(message = "Position is required")
    @Size(min = 2, max = 100, message = "Position must be between 2 and 100 characters")
    @Column(nullable = false)
    private String position;
    
    // Constructors
    public Employee() {}
    
    public Employee(String firstName, String lastName, LocalDate dateOfBirth, 
                   String nationalityIdentity, String email, String passwordHash, 
                   String passwordSalt, String position) {
        super(firstName, lastName, dateOfBirth, nationalityIdentity, email, passwordHash, passwordSalt);
        this.position = position;
    }
    
    // Getters and Setters
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
} 




