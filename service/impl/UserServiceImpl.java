package service.impl;

import dto.UserCreateRequest;
import dto.UserResponse;
import entity.User;
import repository.UserRepository;
import service.IUserService;
import mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse create(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
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
        User user = userRepository.findById(id).orElseThrow();
        user = userMapper.toEntity(request);
        user.setId(id);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
} 