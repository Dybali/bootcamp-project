package com.bootcamp.mapper;

import com.bootcamp.dto.UserCreateRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.entity.User;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class UserMapper {
    
    public User toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setNationalityIdentity(request.getNationalityIdentity());
        user.setEmail(request.getEmail());
        // Password will be handled in service layer
        return user;
    }
    
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        
        return new UserResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getDateOfBirth(),
            user.getNationalityIdentity(),
            user.getEmail()
        );
    }
} 




