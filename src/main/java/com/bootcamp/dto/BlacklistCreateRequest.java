package com.bootcamp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BlacklistCreateRequest {
    @NotBlank(message = "Reason is required")
    @Size(min = 10, max = 500, message = "Reason must be between 10 and 500 characters")
    private String reason;
    
    @NotNull(message = "Applicant ID is required")
    private Long applicantId;
    
    // Constructors
    public BlacklistCreateRequest() {}
    
    public BlacklistCreateRequest(String reason, Long applicantId) {
        this.reason = reason;
        this.applicantId = applicantId;
    }
    
    // Getters and Setters
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
} 




