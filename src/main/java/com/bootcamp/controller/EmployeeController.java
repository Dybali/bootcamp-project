package com.bootcamp.controller;

import com.bootcamp.dto.EmployeeCreateRequest;
import com.bootcamp.dto.EmployeeResponse;
import com.bootcamp.service.IEmployeeService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final IEmployeeService employeeService;
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponse create(@Valid @RequestBody EmployeeCreateRequest request) {
        return employeeService.create(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeService.getAll();
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeCreateRequest request) {
        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
} 




