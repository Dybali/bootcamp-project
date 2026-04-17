package com.bootcamp.service.impl;

import com.bootcamp.dto.ApplicantCreateRequest;
import com.bootcamp.dto.ApplicantResponse;
import com.bootcamp.entity.Applicant;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.ApplicantRepository;
import com.bootcamp.service.IApplicantService;
import com.bootcamp.mapper.ApplicantMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements IApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final PasswordEncoder passwordEncoder;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicantMapper applicantMapper, PasswordEncoder passwordEncoder) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApplicantResponse create(ApplicantCreateRequest request) {
        Applicant applicant = applicantMapper.toEntity(request);
        applicant.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        applicant.setPasswordSalt(null);
        applicant = applicantRepository.save(applicant);
        return applicantMapper.toResponse(applicant);
    }

    @Override
    public ApplicantResponse getById(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Applicant bulunamadı: " + id));
        return applicantMapper.toResponse(applicant);
    }

    @Override
    public List<ApplicantResponse> getAll() {
        return applicantRepository.findAll().stream()
                .map(applicantMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicantResponse update(Long id, ApplicantCreateRequest request) {
        applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Applicant bulunamadı: " + id));

        Applicant applicant = applicantMapper.toEntity(request);
        applicant.setId(id);
        applicant.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        applicant.setPasswordSalt(null);

        applicant = applicantRepository.save(applicant);
        return applicantMapper.toResponse(applicant);
    }

    @Override
    public void delete(Long id) {
        if (!applicantRepository.existsById(id)) {
            throw new EntityNotFoundException("Applicant bulunamadı: " + id);
        }
        applicantRepository.deleteById(id);
    }
} 










