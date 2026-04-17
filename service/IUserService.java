package com.bootcamp.service;

import com.bootcamp.dto.UserCreateRequest;
import com.bootcamp.dto.UserResponse;
import java.util.List;

public interface IUserService {
    UserResponse create(UserCreateRequest request);
    UserResponse getById(Long id);
    List<UserResponse> getAll();
    UserResponse update(Long id, UserCreateRequest request);
    void delete(Long id);
} 


