package service;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import java.util.List;

public interface IEmployeeService {
    EmployeeResponse create(EmployeeCreateRequest request);
    EmployeeResponse getById(Long id);
    List<EmployeeResponse> getAll();
    EmployeeResponse update(Long id, EmployeeCreateRequest request);
    void delete(Long id);
} 