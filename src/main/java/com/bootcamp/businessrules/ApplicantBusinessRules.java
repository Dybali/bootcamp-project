package com.bootcamp.businessrules;

import com.bootcamp.entity.Applicant;
import com.bootcamp.entity.Blacklist;
import com.bootcamp.exception.BusinessException;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.ApplicantRepository;
import com.bootcamp.repository.BlacklistRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ApplicantBusinessRules {
    private final ApplicantRepository applicantRepository;
    private final BlacklistRepository blacklistRepository;
    
    public ApplicantBusinessRules(ApplicantRepository applicantRepository, BlacklistRepository blacklistRepository) {
        this.applicantRepository = applicantRepository;
        this.blacklistRepository = blacklistRepository;
    }
    
    public void checkIfNationalityIdentityExists(String nationalityIdentity) {
        Optional<Applicant> existingApplicant = applicantRepository.findByNationalityIdentity(nationalityIdentity);
        if (existingApplicant.isPresent()) {
            throw new BusinessException("Bu TC kimlik numarası ile zaten kayıtlı bir başvuru sahibi bulunmaktadır.");
        }
    }
    
    public void checkIfBlacklisted(Long applicantId) {
        Optional<Blacklist> blacklistEntry = blacklistRepository.findByApplicantId(applicantId);
        if (blacklistEntry.isPresent()) {
            throw new BusinessException("Bu başvuru sahibi kara listede bulunmaktadır. Başvuru yapamaz.");
        }
    }
    
    public void checkIfApplicantExists(Long applicantId) {
        Optional<Applicant> applicant = applicantRepository.findById(applicantId);
        if (applicant.isEmpty()) {
            throw new EntityNotFoundException("Sistemde kayıtlı olmayan başvuru sahibi ID'si: " + applicantId);
        }
    }
} 




