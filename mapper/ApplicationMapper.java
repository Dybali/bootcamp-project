package mapper;

import dto.ApplicationCreateRequest;
import dto.ApplicationResponse;
import entity.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    Application toEntity(ApplicationCreateRequest request);
    ApplicationResponse toResponse(Application application);
} 