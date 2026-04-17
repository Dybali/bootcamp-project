package com.bootcamp.dto;

import javax.validation.constraints.NotNull;

public class ApplicationCreateRequest {
    @NotNull(message = "Applicant ID is required")
    private Long applicantId;
    
    @NotNull(message = "Bootcamp ID is required")
    private Long bootcampId;
    
    // Constructors
    public ApplicationCreateRequest() {}
    
    public ApplicationCreateRequest(Long applicantId, Long bootcampId) {
        this.applicantId = applicantId;
        this.bootcampId = bootcampId;
    }
    
    // Getters and Setters
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
    
    public Long getBootcampId() { return bootcampId; }
    public void setBootcampId(Long bootcampId) { this.bootcampId = bootcampId; }
} 




