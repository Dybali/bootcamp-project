package service.impl;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import entity.Employee;
import repository.EmployeeRepository;
import service.IEmployeeService;
import mapper.EmployeeMapper;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeResponse create(EmployeeCreateRequest request) {
        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
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
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee = employeeMapper.toEntity(request);
        employee.setId(id);
        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
} 