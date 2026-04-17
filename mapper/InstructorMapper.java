package com.bootcamp.mapper;

import com.bootcamp.dto.InstructorCreateRequest;
import com.bootcamp.dto.InstructorResponse;
import com.bootcamp.entity.Instructor;
import com.bootcamp.org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    Instructor toEntity(InstructorCreateRequest request);
    InstructorResponse toResponse(Instructor instructor);
} 


