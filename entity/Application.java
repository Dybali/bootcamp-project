package com.bootcamp.entity;

import com.bootcamp.javax.persistence.*;
import com.bootcamp.javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Applicant ID is required")
    @Column(name = "applicant_id", nullable = false)
    private Long applicantId;
    
    @NotNull(message = "Bootcamp ID is required")
    @Column(name = "bootcamp_id", nullable = false)
    private Long bootcampId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "application_state", nullable = false)
    private ApplicationState applicationState = ApplicationState.PENDING;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Application() {}
    
    public Application(Long applicantId, Long bootcampId) {
        this.applicantId = applicantId;
        this.bootcampId = bootcampId;
        this.applicationState = ApplicationState.PENDING;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
    
    public Long getBootcampId() { return bootcampId; }
    public void setBootcampId(Long bootcampId) { this.bootcampId = bootcampId; }
    
    public ApplicationState getApplicationState() { return applicationState; }
    public void setApplicationState(ApplicationState applicationState) { 
        this.applicationState = applicationState;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
} 


