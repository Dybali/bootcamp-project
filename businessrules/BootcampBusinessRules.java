package businessrules;

import org.springframework.stereotype.Component;

@Component
public class BootcampBusinessRules {
    public void checkIfStartDateBeforeEndDate() {
        // Başlangıç tarihi, bitiş tarihinden önce olmalı
    }
    public void checkIfBootcampNameExists(String name) {
        // Aynı isimde bootcamp kontrolü
    }
    public void checkIfInstructorExists(Long instructorId) {
        // Eğitmen sistemde kayıtlı mı kontrolü
    }
    public void checkIfBootcampOpenForApplication(Long bootcampId) {
        // Başvuru durumu "CLOSED" olan bootcamp’e başvuru alınamaz
    }
} 