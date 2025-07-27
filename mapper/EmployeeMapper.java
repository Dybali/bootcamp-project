package mapper;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeCreateRequest request);
    EmployeeResponse toResponse(Employee employee);
} 