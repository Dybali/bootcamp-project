package com.bootcamp.dto;

import java.time.LocalDate;

public class BlacklistResponse {
    private Long id;
    private String reason;
    private LocalDate date;
    private Long applicantId;
    
    // Constructors
    public BlacklistResponse() {}
    
    public BlacklistResponse(Long id, String reason, LocalDate date, Long applicantId) {
        this.id = id;
        this.reason = reason;
        this.date = date;
        this.applicantId = applicantId;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
} 




