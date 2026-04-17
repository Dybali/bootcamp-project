package com.bootcamp.service;

import com.bootcamp.dto.BootcampCreateRequest;
import com.bootcamp.dto.BootcampResponse;
import java.util.List;

public interface IBootcampService {
    BootcampResponse create(BootcampCreateRequest request);
    BootcampResponse getById(Long id);
    List<BootcampResponse> getAll();
    BootcampResponse update(Long id, BootcampCreateRequest request);
    void delete(Long id);
}




