package businessrules;

import org.springframework.stereotype.Component;

@Component
public class ApplicantBusinessRules {
    public void checkIfNationalityIdentityExists(String nationalityIdentity) {
        // Aynı TC ile başvuru kontrolü
    }
    public void checkIfBlacklisted(Long applicantId) {
        // Kara liste kontrolü
    }
    public void checkIfApplicantExists(Long applicantId) {
        // Sistemde kayıtlı olmayan başvuru sahibi kontrolü
    }
} 