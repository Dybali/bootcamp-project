package com.bootcamp.service;

import com.bootcamp.dto.EmployeeCreateRequest;
import com.bootcamp.dto.EmployeeResponse;
import java.util.List;

public interface IEmployeeService {
    EmployeeResponse create(EmployeeCreateRequest request);
    EmployeeResponse getById(Long id);
    List<EmployeeResponse> getAll();
    EmployeeResponse update(Long id, EmployeeCreateRequest request);
    void delete(Long id);
} 


