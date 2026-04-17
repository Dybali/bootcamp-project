package com.bootcamp.mapper;

import com.bootcamp.dto.EmployeeCreateRequest;
import com.bootcamp.dto.EmployeeResponse;
import com.bootcamp.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeCreateRequest request);
    EmployeeResponse toResponse(Employee employee);
} 




