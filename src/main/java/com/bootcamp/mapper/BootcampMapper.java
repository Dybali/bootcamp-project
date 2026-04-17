package com.bootcamp.mapper;

import com.bootcamp.dto.BootcampCreateRequest;
import com.bootcamp.dto.BootcampResponse;
import com.bootcamp.entity.Bootcamp;
import com.bootcamp.entity.BootcampState;
import org.springframework.stereotype.Component;

@Component
public class BootcampMapper {
    
    public Bootcamp toEntity(BootcampCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setName(request.getName());
        bootcamp.setInstructorId(request.getInstructorId());
        bootcamp.setStartDate(request.getStartDate());
        bootcamp.setEndDate(request.getEndDate());
        bootcamp.setBootcampState(BootcampState.PREPARING);
        return bootcamp;
    }
    
    public BootcampResponse toResponse(Bootcamp bootcamp) {
        if (bootcamp == null) {
            return null;
        }
        
        return new BootcampResponse(
            bootcamp.getId(),
            bootcamp.getName(),
            bootcamp.getInstructorId(),
            bootcamp.getStartDate(),
            bootcamp.getEndDate(),
            bootcamp.getBootcampState()
        );
    }
}




