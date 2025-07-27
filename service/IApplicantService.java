package service;

import dto.ApplicantCreateRequest;
import dto.ApplicantResponse;
import java.util.List;

public interface IApplicantService {
    ApplicantResponse create(ApplicantCreateRequest request);
    ApplicantResponse getById(Long id);
    List<ApplicantResponse> getAll();
    ApplicantResponse update(Long id, ApplicantCreateRequest request);
    void delete(Long id);
} 