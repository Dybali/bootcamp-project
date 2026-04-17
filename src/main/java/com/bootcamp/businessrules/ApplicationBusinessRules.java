package com.bootcamp.businessrules;

import com.bootcamp.entity.Application;
import com.bootcamp.entity.Bootcamp;
import com.bootcamp.entity.Blacklist;
import com.bootcamp.entity.ApplicationState;
import com.bootcamp.entity.BootcampState;
import com.bootcamp.exception.BusinessException;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.ApplicationRepository;
import com.bootcamp.repository.BootcampRepository;
import com.bootcamp.repository.BlacklistRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ApplicationBusinessRules {
    private final ApplicationRepository applicationRepository;
    private final BootcampRepository bootcampRepository;
    private final BlacklistRepository blacklistRepository;
    
    public ApplicationBusinessRules(ApplicationRepository applicationRepository, 
                                  BootcampRepository bootcampRepository, 
                                  BlacklistRepository blacklistRepository) {
        this.applicationRepository = applicationRepository;
        this.bootcampRepository = bootcampRepository;
        this.blacklistRepository = blacklistRepository;
    }
    
    public void checkIfApplicantAppliedToBootcamp(Long applicantId, Long bootcampId) {
        Optional<Application> existingApplication = applicationRepository.findByApplicantIdAndBootcampId(applicantId, bootcampId);
        if (existingApplication.isPresent()) {
            throw new BusinessException("Bu başvuru sahibi zaten bu bootcamp'e başvuru yapmıştır.");
        }
    }
    
    public void checkIfBootcampIsActive(Long bootcampId) {
        Optional<Bootcamp> bootcamp = bootcampRepository.findById(bootcampId);
        if (bootcamp.isEmpty()) {
            throw new EntityNotFoundException("Bootcamp bulunamadı: " + bootcampId);
        }
        
        BootcampState state = bootcamp.get().getBootcampState();
        if (state != BootcampState.OPEN_FOR_APPLICATION) {
            throw new BusinessException("Bu bootcamp başvuru almıyor. Mevcut durum: " + state);
        }
    }
    
    public void checkIfApplicantBlacklisted(Long applicantId) {
        Optional<Blacklist> blacklistEntry = blacklistRepository.findByApplicantId(applicantId);
        if (blacklistEntry.isPresent()) {
            throw new BusinessException("Bu başvuru sahibi kara listede bulunmaktadır. Başvuru yapamaz.");
        }
    }
    
    public void checkIfStatusTransitionValid(ApplicationState currentState, ApplicationState newState) {
        boolean isValidTransition = switch (currentState) {
            case PENDING -> newState == ApplicationState.IN_REVIEW || newState == ApplicationState.CANCELLED;
            case IN_REVIEW -> newState == ApplicationState.APPROVED || newState == ApplicationState.REJECTED || newState == ApplicationState.CANCELLED;
            case APPROVED, REJECTED, CANCELLED -> false; // Final states
        };
        
        if (!isValidTransition) {
            throw new BusinessException("Geçersiz durum geçişi: " + currentState + " -> " + newState);
        }
    }
} 




