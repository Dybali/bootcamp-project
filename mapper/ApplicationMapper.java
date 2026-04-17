package com.bootcamp.mapper;

import com.bootcamp.dto.ApplicationCreateRequest;
import com.bootcamp.dto.ApplicationResponse;
import com.bootcamp.entity.Application;
import com.bootcamp.org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    Application toEntity(ApplicationCreateRequest request);
    ApplicationResponse toResponse(Application application);
} 


