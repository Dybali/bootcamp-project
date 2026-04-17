package com.bootcamp.service;

import com.bootcamp.dto.BlacklistCreateRequest;
import com.bootcamp.dto.BlacklistResponse;
import java.util.List;

public interface IBlacklistService {
    BlacklistResponse create(BlacklistCreateRequest request);
    BlacklistResponse getById(Long id);
    List<BlacklistResponse> getAll();
    BlacklistResponse update(Long id, BlacklistCreateRequest request);
    void delete(Long id);
} 


