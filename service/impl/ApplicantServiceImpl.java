package service.impl;

import com.bootcamp.dto.ApplicantCreateRequest;
import com.bootcamp.dto.ApplicantResponse;
import com.bootcamp.entity.Applicant;
import com.bootcamp.repository.ApplicantRepository;
import com.bootcamp.service.IApplicantService;
import com.bootcamp.mapper.ApplicantMapper;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicantServiceImpl implements IApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
    }

    @Override
    public ApplicantResponse create(ApplicantCreateRequest request) {
        Applicant applicant = applicantMapper.toEntity(request);
        applicant = applicantRepository.save(applicant);
        return applicantMapper.toResponse(applicant);
    }

    @Override
    public ApplicantResponse getById(Long id) {
        Applicant applicant = applicantRepository.findById(id).orElseThrow();
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
        Applicant applicant = applicantRepository.findById(id).orElseThrow();
        applicant = applicantMapper.toEntity(request);
        applicant.setId(id);
        applicant = applicantRepository.save(applicant);
        return applicantMapper.toResponse(applicant);
    }

    @Override
    public void delete(Long id) {
        applicantRepository.deleteById(id);
    }
} 


