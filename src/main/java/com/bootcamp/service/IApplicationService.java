package com.bootcamp.service;

import com.bootcamp.dto.ApplicationCreateRequest;
import com.bootcamp.dto.ApplicationResponse;
import java.util.List;

public interface IApplicationService {
    ApplicationResponse create(ApplicationCreateRequest request);
    ApplicationResponse getById(Long id);
    List<ApplicationResponse> getAll();
    ApplicationResponse update(Long id, ApplicationCreateRequest request);
    void delete(Long id);
} 




