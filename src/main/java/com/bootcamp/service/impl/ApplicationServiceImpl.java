package com.bootcamp.service.impl;

import com.bootcamp.dto.ApplicationCreateRequest;
import com.bootcamp.dto.ApplicationResponse;
import com.bootcamp.entity.Application;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.ApplicationRepository;
import com.bootcamp.service.IApplicationService;
import com.bootcamp.mapper.ApplicationMapper;
import com.bootcamp.businessrules.ApplicationBusinessRules;
import com.bootcamp.businessrules.ApplicantBusinessRules;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ApplicationBusinessRules applicationBusinessRules;
    private final ApplicantBusinessRules applicantBusinessRules;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            ApplicationMapper applicationMapper,
            ApplicationBusinessRules applicationBusinessRules,
            ApplicantBusinessRules applicantBusinessRules
    ) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.applicationBusinessRules = applicationBusinessRules;
        this.applicantBusinessRules = applicantBusinessRules;
    }

    @Override
    public ApplicationResponse create(ApplicationCreateRequest request) {
        applicantBusinessRules.checkIfApplicantExists(request.getApplicantId());
        applicantBusinessRules.checkIfBlacklisted(request.getApplicantId());
        applicationBusinessRules.checkIfApplicantAppliedToBootcamp(request.getApplicantId(), request.getBootcampId());
        applicationBusinessRules.checkIfBootcampIsActive(request.getBootcampId());

        Application application = applicationMapper.toEntity(request);
        application = applicationRepository.save(application);
        return applicationMapper.toResponse(application);
    }

    @Override
    public ApplicationResponse getById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application bulunamadı: " + id));
        return applicationMapper.toResponse(application);
    }

    @Override
    public List<ApplicationResponse> getAll() {
        return applicationRepository.findAll().stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponse update(Long id, ApplicationCreateRequest request) {
        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application bulunamadı: " + id));

        applicantBusinessRules.checkIfApplicantExists(request.getApplicantId());
        applicantBusinessRules.checkIfBlacklisted(request.getApplicantId());
        if (!existing.getApplicantId().equals(request.getApplicantId())
                || !existing.getBootcampId().equals(request.getBootcampId())) {
            applicationBusinessRules.checkIfApplicantAppliedToBootcamp(request.getApplicantId(), request.getBootcampId());
        }
        applicationBusinessRules.checkIfBootcampIsActive(request.getBootcampId());

        Application application = applicationMapper.toEntity(request);
        application.setId(id);
        application = applicationRepository.save(application);
        return applicationMapper.toResponse(application);
    }

    @Override
    public void delete(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new EntityNotFoundException("Application bulunamadı: " + id);
        }
        applicationRepository.deleteById(id);
    }
} 










