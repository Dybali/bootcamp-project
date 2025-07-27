package service.impl;

import dto.InstructorCreateRequest;
import dto.InstructorResponse;
import entity.Instructor;
import repository.InstructorRepository;
import service.IInstructorService;
import mapper.InstructorMapper;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorServiceImpl implements IInstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    @Override
    public InstructorResponse create(InstructorCreateRequest request) {
        Instructor instructor = instructorMapper.toEntity(request);
        instructor = instructorRepository.save(instructor);
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public InstructorResponse getById(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow();
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
        Instructor instructor = instructorRepository.findById(id).orElseThrow();
        instructor = instructorMapper.toEntity(request);
        instructor.setId(id);
        instructor = instructorRepository.save(instructor);
        return instructorMapper.toResponse(instructor);
    }

    @Override
    public void delete(Long id) {
        instructorRepository.deleteById(id);
    }
} 