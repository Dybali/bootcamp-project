package com.bootcamp.service;

import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.LoginRequest;

public interface AuthService {
    void register(RegisterRequest request);
    String login(LoginRequest request);
} 


