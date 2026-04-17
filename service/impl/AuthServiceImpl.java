package com.bootcamp.service.impl;

import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.LoginRequest;
import com.bootcamp.entity.User;
import com.bootcamp.repository.UserRepository;
import com.bootcamp.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    @Override
    public void register(RegisterRequest request) {
        // Email kontrolü
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Bu email adresi zaten kayıtlı: " + request.getEmail());
        }
        
        // Şifre hash'leme
        String salt = UUID.randomUUID().toString();
        String hashedPassword = passwordEncoder.encode(request.getPassword() + salt);
        
        // Yeni kullanıcı oluştur
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashedPassword);
        user.setPasswordSalt(salt);
        user.setDateOfBirth(java.time.LocalDate.now().minusYears(25)); // Default
        user.setNationalityIdentity("00000000000"); // Default
        
        userRepository.save(user);
    }
    
    @Override
    public String login(LoginRequest request) {
        // Kullanıcıyı bul
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Geçersiz email veya şifre");
        }
        
        User user = userOpt.get();
        
        // Şifre kontrolü
        String inputPassword = request.getPassword() + user.getPasswordSalt();
        if (!passwordEncoder.matches(inputPassword, user.getPasswordHash())) {
            throw new RuntimeException("Geçersiz email veya şifre");
        }
        
        // Basit token oluştur (gerçek uygulamada JWT kullanılmalı)
        return "Bearer " + UUID.randomUUID().toString();
    }
}



