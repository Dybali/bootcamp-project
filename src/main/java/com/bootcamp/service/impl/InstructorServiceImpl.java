package com.bootcamp.service.impl;

import com.bootcamp.dto.InstructorCreateRequest;
import com.bootcamp.dto.InstructorResponse;
import com.bootcamp.entity.Instructor;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.InstructorRepository;
import com.bootcamp.service.IInstructorService;
import com.bootcamp.mapper.InstructorMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements IInstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final PasswordEncoder passwordEncoder;

    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper, PasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public InstructorResponse create(InstructorCreateRequest request) {
        Instructor instructor = instructorMapper.toEntity(request);
        instructor.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        instructor.setPasswordSalt(null);
        instructor = instructorRepository.save(instructor);
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public InstructorResponse getById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor bulunamadı: " + id));
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public List<InstructorResponse> getAll() {
        return instructorRepository.findAll().stream()
                .map(instructorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorResponse update(Long id, InstructorCreateRequest request) {
        instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor bulunamadı: " + id));

        Instructor instructor = instructorMapper.toEntity(request);
        instructor.setId(id);
        instructor.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        instructor.setPasswordSalt(null);

        instructor = instructorRepository.save(instructor);
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public void delete(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new EntityNotFoundException("Instructor bulunamadı: " + id);
        }
        instructorRepository.deleteById(id);
    }
} 










