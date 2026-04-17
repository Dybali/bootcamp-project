package com.bootcamp.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "applicants")
@PrimaryKeyJoinColumn(name = "user_id")
public class Applicant extends User {
    
    @Size(max = 1000, message = "About section cannot exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String about;
    
    // Constructors
    public Applicant() {}
    
    public Applicant(String firstName, String lastName, LocalDate dateOfBirth, 
                    String nationalityIdentity, String email, String passwordHash, 
                    String passwordSalt, String about) {
        super(firstName, lastName, dateOfBirth, nationalityIdentity, email, passwordHash, passwordSalt);
        this.about = about;
    }
    
    // Getters and Setters
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
} 




