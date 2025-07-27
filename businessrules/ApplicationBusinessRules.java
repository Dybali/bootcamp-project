package businessrules;

import org.springframework.stereotype.Component;

@Component
public class ApplicationBusinessRules {
    public void checkIfApplicantAppliedToBootcamp(Long applicantId, Long bootcampId) {
        // Aynı kişi aynı bootcamp’e birden fazla başvuru yapamaz
    }
    public void checkIfBootcampIsActive(Long bootcampId) {
        // Başvuru yapılan bootcamp aktif olmalı
    }
    public void checkIfApplicantBlacklisted(Long applicantId) {
        // Kara liste kontrolü
    }
    public void checkIfStatusTransitionValid() {
        // Başvurunun durumu sadece belirli statülere geçirilebilir
    }
} 