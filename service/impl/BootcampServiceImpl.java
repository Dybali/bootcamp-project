package service.impl;

import com.bootcamp.dto.BootcampCreateRequest;
import com.bootcamp.dto.BootcampResponse;
import com.bootcamp.entity.Bootcamp;
import com.bootcamp.repository.BootcampRepository;
import com.bootcamp.service.IBootcampService;
import com.bootcamp.mapper.BootcampMapper;
import com.bootcamp.businessrules.BootcampBusinessRules;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BootcampServiceImpl implements IBootcampService {
    private final BootcampRepository bootcampRepository;
    private final BootcampMapper bootcampMapper;
    private final BootcampBusinessRules bootcampBusinessRules;

    public BootcampServiceImpl(BootcampRepository bootcampRepository, 
                              BootcampMapper bootcampMapper,
                              BootcampBusinessRules bootcampBusinessRules) {
        this.bootcampRepository = bootcampRepository;
        this.bootcampMapper = bootcampMapper;
        this.bootcampBusinessRules = bootcampBusinessRules;
    }

    @Override
    public BootcampResponse create(BootcampCreateRequest request) {
        // Business rules kontrolü
        bootcampBusinessRules.checkIfStartDateBeforeEndDate(request.getStartDate(), request.getEndDate());
        bootcampBusinessRules.checkIfBootcampNameExists(request.getName());
        bootcampBusinessRules.checkIfInstructorExists(request.getInstructorId());
        
        Bootcamp bootcamp = bootcampMapper.toEntity(request);
        bootcamp = bootcampRepository.save(bootcamp);
        return bootcampMapper.toResponse(bootcamp);
    }

    @Override
    public BootcampResponse getById(Long id) {
        Bootcamp bootcamp = bootcampRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bootcamp bulunamadı: " + id));
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
        Bootcamp existingBootcamp = bootcampRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bootcamp bulunamadı: " + id));
        
        // Business rules kontrolü
        bootcampBusinessRules.checkIfStartDateBeforeEndDate(request.getStartDate(), request.getEndDate());
        bootcampBusinessRules.checkIfInstructorExists(request.getInstructorId());
        
        Bootcamp bootcamp = bootcampMapper.toEntity(request);
        bootcamp.setId(id);
        bootcamp = bootcampRepository.save(bootcamp);
        return bootcampMapper.toResponse(bootcamp);
    }

    @Override
    public void delete(Long id) {
        if (!bootcampRepository.existsById(id)) {
            throw new RuntimeException("Bootcamp bulunamadı: " + id);
        }
        bootcampRepository.deleteById(id);
    }
}


