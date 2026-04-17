package com.bootcamp.dto;

import com.bootcamp.entity.ApplicationState;
import java.time.LocalDateTime;

public class ApplicationResponse {
    private Long id;
    private Long applicantId;
    private Long bootcampId;
    private ApplicationState applicationState;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public ApplicationResponse() {}
    
    public ApplicationResponse(Long id, Long applicantId, Long bootcampId, 
                              ApplicationState applicationState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.applicantId = applicantId;
        this.bootcampId = bootcampId;
        this.applicationState = applicationState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
    
    public Long getBootcampId() { return bootcampId; }
    public void setBootcampId(Long bootcampId) { this.bootcampId = bootcampId; }
    
    public ApplicationState getApplicationState() { return applicationState; }
    public void setApplicationState(ApplicationState applicationState) { this.applicationState = applicationState; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
} 




