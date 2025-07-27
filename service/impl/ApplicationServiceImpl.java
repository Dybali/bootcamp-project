package service.impl;

import dto.ApplicationCreateRequest;
import dto.ApplicationResponse;
import entity.Application;
import repository.ApplicationRepository;
import service.IApplicationService;
import mapper.ApplicationMapper;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationServiceImpl implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationResponse create(ApplicationCreateRequest request) {
        Application application = applicationMapper.toEntity(request);
        application = applicationRepository.save(application);
        return applicationMapper.toResponse(application);
    }

    @Override
    public ApplicationResponse getById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        return applicationMapper.toResponse(application);
    }

    @Override
    public List<ApplicationResponse> getAll() {
        return applicationRepository.findAll().stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponse update(Long id, ApplicationCreateRequest request) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application = applicationMapper.toEntity(request);
        application.setId(id);
        application = applicationRepository.save(application);
        return applicationMapper.toResponse(application);
    }

    @Override
    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
} 