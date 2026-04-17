package com.bootcamp.service.impl;

import com.bootcamp.dto.EmployeeCreateRequest;
import com.bootcamp.dto.EmployeeResponse;
import com.bootcamp.entity.Employee;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.EmployeeRepository;
import com.bootcamp.service.IEmployeeService;
import com.bootcamp.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeResponse create(EmployeeCreateRequest request) {
        Employee employee = employeeMapper.toEntity(request);
        employee.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        employee.setPasswordSalt(null);
        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee bulunamadı: " + id));
        return employeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeCreateRequest request) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee bulunamadı: " + id));

        Employee employee = employeeMapper.toEntity(request);
        employee.setId(id);
        employee.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        employee.setPasswordSalt(null);

        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee bulunamadı: " + id);
        }
        employeeRepository.deleteById(id);
    }
} 










