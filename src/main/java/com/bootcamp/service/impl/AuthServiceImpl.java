package com.bootcamp.service.impl;

import com.bootcamp.dto.AuthResponse;
import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.LoginRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.exception.BusinessException;
import com.bootcamp.core.security.JwtService;
import com.bootcamp.entity.User;
import com.bootcamp.repository.UserRepository;
import com.bootcamp.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    
    @Override
    public void register(RegisterRequest request) {
        // Email kontrolü
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new BusinessException("Bu email adresi zaten kayıtlı: " + request.getEmail());
        }
        
        // Yeni kullanıcı oluştur
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setNationalityIdentity(request.getNationalityIdentity());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPasswordSalt(null);
        
        userRepository.save(user);
    }
    
    @Override
    public AuthResponse login(LoginRequest request) {
        // Kullanıcıyı bul
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new BusinessException("Geçersiz email veya şifre");
        }
        
        User user = userOpt.get();
        
        // Şifre kontrolü
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BusinessException("Geçersiz email veya şifre");
        }
        
        String token = jwtService.generateToken(user.getEmail());
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getNationalityIdentity(),
                user.getEmail()
        );

        return new AuthResponse(token, userResponse);
    }
}











