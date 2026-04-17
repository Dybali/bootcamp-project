package com.bootcamp.service.impl;

import com.bootcamp.dto.UserCreateRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.entity.User;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.UserRepository;
import com.bootcamp.service.IUserService;
import com.bootcamp.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse create(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPasswordSalt(null);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User bulunamadı: " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse update(Long id, UserCreateRequest request) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User bulunamadı: " + id));

        User user = userMapper.toEntity(request);
        user.setId(id);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPasswordSalt(null);

        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User bulunamadı: " + id);
        }
        userRepository.deleteById(id);
    }
} 










