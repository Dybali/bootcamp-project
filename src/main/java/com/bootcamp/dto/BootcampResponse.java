package com.bootcamp.dto;

import com.bootcamp.entity.BootcampState;
import java.time.LocalDate;

public class BootcampResponse {
    private Long id;
    private String name;
    private Long instructorId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BootcampState bootcampState;
    
    // Constructors
    public BootcampResponse() {}
    
    public BootcampResponse(Long id, String name, Long instructorId, LocalDate startDate, 
                           LocalDate endDate, BootcampState bootcampState) {
        this.id = id;
        this.name = name;
        this.instructorId = instructorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bootcampState = bootcampState;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public BootcampState getBootcampState() { return bootcampState; }
    public void setBootcampState(BootcampState bootcampState) { this.bootcampState = bootcampState; }
} 




