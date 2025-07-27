package dto;

import entity.BootcampState;
import java.time.LocalDate;

public class BootcampResponse {
    private Long id;
    private String name;
    private Long instructorId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BootcampState bootcampState;
    // getter-setter
} 