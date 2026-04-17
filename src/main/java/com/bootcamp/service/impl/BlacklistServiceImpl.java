package com.bootcamp.service.impl;

import com.bootcamp.dto.BlacklistCreateRequest;
import com.bootcamp.dto.BlacklistResponse;
import com.bootcamp.entity.Blacklist;
import com.bootcamp.exception.EntityNotFoundException;
import com.bootcamp.repository.BlacklistRepository;
import com.bootcamp.service.IBlacklistService;
import com.bootcamp.mapper.BlacklistMapper;
import com.bootcamp.businessrules.ApplicantBusinessRules;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class BlacklistServiceImpl implements IBlacklistService {
    private final BlacklistRepository blacklistRepository;
    private final BlacklistMapper blacklistMapper;
    private final ApplicantBusinessRules applicantBusinessRules;

    public BlacklistServiceImpl(
            BlacklistRepository blacklistRepository,
            BlacklistMapper blacklistMapper,
            ApplicantBusinessRules applicantBusinessRules
    ) {
        this.blacklistRepository = blacklistRepository;
        this.blacklistMapper = blacklistMapper;
        this.applicantBusinessRules = applicantBusinessRules;
    }

    @Override
    public BlacklistResponse create(BlacklistCreateRequest request) {
        applicantBusinessRules.checkIfApplicantExists(request.getApplicantId());

        Blacklist blacklist = blacklistMapper.toEntity(request);
        // DTO'da date yok; entity'de zorunlu
        blacklist.setDate(LocalDate.now());
        blacklist = blacklistRepository.save(blacklist);
        return blacklistMapper.toResponse(blacklist);
    }

    @Override
    public BlacklistResponse getById(Long id) {
        Blacklist blacklist = blacklistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blacklist bulunamadı: " + id));
        return blacklistMapper.toResponse(blacklist);
    }

    @Override
    public List<BlacklistResponse> getAll() {
        return blacklistRepository.findAll().stream()
                .map(blacklistMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlacklistResponse update(Long id, BlacklistCreateRequest request) {
        blacklistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blacklist bulunamadı: " + id));

        applicantBusinessRules.checkIfApplicantExists(request.getApplicantId());

        blacklist = blacklistMapper.toEntity(request);
        blacklist.setId(id);
        blacklist.setDate(LocalDate.now());
        blacklist = blacklistRepository.save(blacklist);
        return blacklistMapper.toResponse(blacklist);
    }

    @Override
    public void delete(Long id) {
        if (!blacklistRepository.existsById(id)) {
            throw new EntityNotFoundException("Blacklist bulunamadı: " + id);
        }
        blacklistRepository.deleteById(id);
    }
} 










