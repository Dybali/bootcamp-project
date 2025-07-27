package controller;

import dto.ApplicationCreateRequest;
import dto.ApplicationResponse;
import service.IApplicationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final IApplicationService applicationService;
    public ApplicationController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponse create(@RequestBody ApplicationCreateRequest request) {
        return applicationService.create(request);
    }

    @GetMapping("/{id}")
    public ApplicationResponse getById(@PathVariable Long id) {
        return applicationService.getById(id);
    }

    @GetMapping
    public List<ApplicationResponse> getAll() {
        return applicationService.getAll();
    }

    @PutMapping("/{id}")
    public ApplicationResponse update(@PathVariable Long id, @RequestBody ApplicationCreateRequest request) {
        return applicationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        applicationService.delete(id);
    }
} 