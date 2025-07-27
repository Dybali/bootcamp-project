package service.impl;

import dto.BootcampCreateRequest;
import dto.BootcampResponse;
import entity.Bootcamp;
import repository.BootcampRepository;
import service.IBootcampService;
import mapper.BootcampMapper;
import java.util.List;
import java.util.stream.Collectors;

public class BootcampServiceImpl implements IBootcampService {
    private final BootcampRepository bootcampRepository;
    private final BootcampMapper bootcampMapper;

    public BootcampServiceImpl(BootcampRepository bootcampRepository, BootcampMapper bootcampMapper) {
        this.bootcampRepository = bootcampRepository;
        this.bootcampMapper = bootcampMapper;
    }

    @Override
    public BootcampResponse create(BootcampCreateRequest request) {
        Bootcamp bootcamp = bootcampMapper.toEntity(request);
        bootcamp = bootcampRepository.save(bootcamp);
        return bootcampMapper.toResponse(bootcamp);
    }

    @Override
    public BootcampResponse getById(Long id) {
        Bootcamp bootcamp = bootcampRepository.findById(id).orElseThrow();
        return bootcampMapper.toResponse(bootcamp);
    }

    @Override
    public List<BootcampResponse> getAll() {
        return bootcampRepository.findAll().stream()
                .map(bootcampMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BootcampResponse update(Long id, BootcampCreateRequest request) {
        Bootcamp bootcamp = bootcampRepository.findById(id).orElseThrow();
        bootcamp = bootcampMapper.toEntity(request);
        bootcamp.setId(id);
        bootcamp = bootcampRepository.save(bootcamp);
        return bootcampMapper.toResponse(bootcamp);
    }

    @Override
    public void delete(Long id) {
        bootcampRepository.deleteById(id);
    }
} 