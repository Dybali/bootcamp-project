package com.bootcamp.businessrules;

import org.springframework.stereotype.Component;

@Component
public class BlacklistBusinessRules {
    public void checkIfApplicantHasActiveBlacklist(Long applicantId) {
        // Aynı aday için birden fazla aktif kara liste kaydı olamaz
    }
    public void checkIfReasonNotEmpty(String reason) {
        // Sebep (reason) boş bırakılamaz
    }
} 




