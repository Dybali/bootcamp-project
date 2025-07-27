package controller;

import dto.BlacklistCreateRequest;
import dto.BlacklistResponse;
import service.IBlacklistService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blacklists")
public class BlacklistController {
    private final IBlacklistService blacklistService;
    public BlacklistController(IBlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @PostMapping
    public BlacklistResponse create(@RequestBody BlacklistCreateRequest request) {
        return blacklistService.create(request);
    }

    @GetMapping("/{id}")
    public BlacklistResponse getById(@PathVariable Long id) {
        return blacklistService.getById(id);
    }

    @GetMapping
    public List<BlacklistResponse> getAll() {
        return blacklistService.getAll();
    }

    @PutMapping("/{id}")
    public BlacklistResponse update(@PathVariable Long id, @RequestBody BlacklistCreateRequest request) {
        return blacklistService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        blacklistService.delete(id);
    }
} 