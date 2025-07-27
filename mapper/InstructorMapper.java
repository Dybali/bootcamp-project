package mapper;

import dto.InstructorCreateRequest;
import dto.InstructorResponse;
import entity.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    Instructor toEntity(InstructorCreateRequest request);
    InstructorResponse toResponse(Instructor instructor);
} 