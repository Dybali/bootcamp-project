package controller;

import dto.BootcampCreateRequest;
import dto.BootcampResponse;
import service.IBootcampService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bootcamps")
public class BootcampController {
    private final IBootcampService bootcampService;
    public BootcampController(IBootcampService bootcampService) {
        this.bootcampService = bootcampService;
    }

    @PostMapping
    public BootcampResponse create(@RequestBody BootcampCreateRequest request) {
        return bootcampService.create(request);
    }

    @GetMapping("/{id}")
    public BootcampResponse getById(@PathVariable Long id) {
        return bootcampService.getById(id);
    }

    @GetMapping
    public List<BootcampResponse> getAll() {
        return bootcampService.getAll();
    }

    @PutMapping("/{id}")
    public BootcampResponse update(@PathVariable Long id, @RequestBody BootcampCreateRequest request) {
        return bootcampService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bootcampService.delete(id);
    }
} 