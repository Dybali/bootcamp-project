package com.bootcamp.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class BootcampCreateRequest {
    @NotBlank(message = "Bootcamp name is required")
    @Size(min = 3, max = 100, message = "Bootcamp name must be between 3 and 100 characters")
    private String name;
    
    @NotNull(message = "Instructor ID is required")
    private Long instructorId;
    
    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    
    // Constructors
    public BootcampCreateRequest() {}
    
    public BootcampCreateRequest(String name, Long instructorId, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.instructorId = instructorId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
} 




