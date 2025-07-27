package service;

import dto.UserCreateRequest;
import dto.UserResponse;
import java.util.List;

public interface IUserService {
    UserResponse create(UserCreateRequest request);
    UserResponse getById(Long id);
    List<UserResponse> getAll();
    UserResponse update(Long id, UserCreateRequest request);
    void delete(Long id);
} 