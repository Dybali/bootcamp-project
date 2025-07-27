package controller;

import dto.ApplicantCreateRequest;
import dto.ApplicantResponse;
import service.IApplicantService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {
    private final IApplicantService applicantService;
    public ApplicantController(IApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping
    public ApplicantResponse create(@RequestBody ApplicantCreateRequest request) {
        return applicantService.create(request);
    }

    @GetMapping("/{id}")
    public ApplicantResponse getById(@PathVariable Long id) {
        return applicantService.getById(id);
    }

    @GetMapping
    public List<ApplicantResponse> getAll() {
        return applicantService.getAll();
    }

    @PutMapping("/{id}")
    public ApplicantResponse update(@PathVariable Long id, @RequestBody ApplicantCreateRequest request) {
        return applicantService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        applicantService.delete(id);
    }
} 