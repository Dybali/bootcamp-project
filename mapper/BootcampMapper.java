package mapper;

import dto.BootcampCreateRequest;
import dto.BootcampResponse;
import entity.Bootcamp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BootcampMapper {
    Bootcamp toEntity(BootcampCreateRequest request);
    BootcampResponse toResponse(Bootcamp bootcamp);
} 