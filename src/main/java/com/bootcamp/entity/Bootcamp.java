package com.bootcamp.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "bootcamps")
public class Bootcamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Bootcamp name is required")
    @Size(min = 3, max = 100, message = "Bootcamp name must be between 3 and 100 characters")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Instructor ID is required")
    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;
    
    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "bootcamp_state", nullable = false)
    private BootcampState bootcampState = BootcampState.PREPARING;
    
    // Constructors
    public Bootcamp() {}
    
    public Bootcamp(String name, Long instructorId, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.instructorId = instructorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bootcampState = BootcampState.PREPARING;
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




