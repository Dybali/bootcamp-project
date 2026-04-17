package com.bootcamp.businessrules;

import com.bootcamp.entity.Bootcamp;
import com.bootcamp.entity.Instructor;
import com.bootcamp.entity.BootcampState;
import com.bootcamp.repository.BootcampRepository;
import com.bootcamp.repository.InstructorRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class BootcampBusinessRules {
    private final BootcampRepository bootcampRepository;
    private final InstructorRepository instructorRepository;
    
    public BootcampBusinessRules(BootcampRepository bootcampRepository, InstructorRepository instructorRepository) {
        this.bootcampRepository = bootcampRepository;
        this.instructorRepository = instructorRepository;
    }
    
    public void checkIfStartDateBeforeEndDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("Başlangıç tarihi, bitiş tarihinden sonra olamaz.");
        }
        
        if (startDate.isEqual(endDate)) {
            throw new RuntimeException("Başlangıç tarihi, bitiş tarihi ile aynı olamaz.");
        }
    }
    
    public void checkIfBootcampNameExists(String name) {
        Optional<Bootcamp> existingBootcamp = bootcampRepository.findByName(name);
        if (existingBootcamp.isPresent()) {
            throw new RuntimeException("Bu isimde zaten bir bootcamp bulunmaktadır: " + name);
        }
    }
    
    public void checkIfInstructorExists(Long instructorId) {
        Optional<Instructor> instructor = instructorRepository.findById(instructorId);
        if (instructor.isEmpty()) {
            throw new RuntimeException("Sistemde kayıtlı olmayan eğitmen ID'si: " + instructorId);
        }
    }
    
    public void checkIfBootcampOpenForApplication(Long bootcampId) {
        Optional<Bootcamp> bootcamp = bootcampRepository.findById(bootcampId);
        if (bootcamp.isEmpty()) {
            throw new RuntimeException("Bootcamp bulunamadı: " + bootcampId);
        }
        
        BootcampState state = bootcamp.get().getBootcampState();
        if (state != BootcampState.OPEN_FOR_APPLICATION) {
            throw new RuntimeException("Bu bootcamp başvuru almıyor. Mevcut durum: " + state);
        }
    }
} 


