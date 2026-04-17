package com.bootcamp.service;

import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.LoginRequest;
import com.bootcamp.dto.AuthResponse;

public interface AuthService {
    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
} 




