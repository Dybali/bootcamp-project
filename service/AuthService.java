package service;

import dto.RegisterRequest;
import dto.LoginRequest;

public interface AuthService {
    void register(RegisterRequest request);
    String login(LoginRequest request);
} 