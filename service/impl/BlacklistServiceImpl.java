package service.impl;

import dto.BlacklistCreateRequest;
import dto.BlacklistResponse;
import entity.Blacklist;
import repository.BlacklistRepository;
import service.IBlacklistService;
import mapper.BlacklistMapper;
import java.util.List;
import java.util.stream.Collectors;

public class BlacklistServiceImpl implements IBlacklistService {
    private final BlacklistRepository blacklistRepository;
    private final BlacklistMapper blacklistMapper;

    public BlacklistServiceImpl(BlacklistRepository blacklistRepository, BlacklistMapper blacklistMapper) {
        this.blacklistRepository = blacklistRepository;
        this.blacklistMapper = blacklistMapper;
    }

    @Override
    public BlacklistResponse create(BlacklistCreateRequest request) {
        Blacklist blacklist = blacklistMapper.toEntity(request);
        blacklist = blacklistRepository.save(blacklist);
        return blacklistMapper.toResponse(blacklist);
    }

    @Override
    public BlacklistResponse getById(Long id) {
        Blacklist blacklist = blacklistRepository.findById(id).orElseThrow();
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
        Blacklist blacklist = blacklistRepository.findById(id).orElseThrow();
        blacklist = blacklistMapper.toEntity(request);
        blacklist.setId(id);
        blacklist = blacklistRepository.save(blacklist);
        return blacklistMapper.toResponse(blacklist);
    }

    @Override
    public void delete(Long id) {
        blacklistRepository.deleteById(id);
    }
} 