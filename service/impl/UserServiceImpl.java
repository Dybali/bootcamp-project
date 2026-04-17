package service.impl;

import com.bootcamp.dto.UserCreateRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.entity.User;
import com.bootcamp.repository.UserRepository;
import com.bootcamp.service.IUserService;
import com.bootcamp.mapper.UserMapper;
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


