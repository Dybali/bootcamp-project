package controller;

import dto.InstructorCreateRequest;
import dto.InstructorResponse;
import service.IInstructorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final IInstructorService instructorService;
    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public InstructorResponse create(@RequestBody InstructorCreateRequest request) {
        return instructorService.create(request);
    }

    @GetMapping("/{id}")
    public InstructorResponse getById(@PathVariable Long id) {
        return instructorService.getById(id);
    }

    @GetMapping
    public List<InstructorResponse> getAll() {
        return instructorService.getAll();
    }

    @PutMapping("/{id}")
    public InstructorResponse update(@PathVariable Long id, @RequestBody InstructorCreateRequest request) {
        return instructorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        instructorService.delete(id);
    }
} 