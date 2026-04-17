package com.bootcamp.service;

import com.bootcamp.dto.InstructorCreateRequest;
import com.bootcamp.dto.InstructorResponse;
import java.util.List;

public interface IInstructorService {
    InstructorResponse create(InstructorCreateRequest request);
    InstructorResponse getById(Long id);
    List<InstructorResponse> getAll();
    InstructorResponse update(Long id, InstructorCreateRequest request);
    void delete(Long id);
} 


