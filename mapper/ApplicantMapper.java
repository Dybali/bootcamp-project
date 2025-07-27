package mapper;

import dto.ApplicantCreateRequest;
import dto.ApplicantResponse;
import entity.Applicant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {
    Applicant toEntity(ApplicantCreateRequest request);
    ApplicantResponse toResponse(Applicant applicant);
} 