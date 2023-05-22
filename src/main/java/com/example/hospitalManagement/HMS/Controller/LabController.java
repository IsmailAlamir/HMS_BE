package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Domain.Test;
import com.example.hospitalManagement.HMS.Domain.Visit;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lab")
@PreAuthorize("hasRole('LAB')")
public class LabController {
    private final VisitRepository visitRepository;
    private final TestRepository testRepository;

    public LabController(
            VisitRepository visitRepository,
            TestRepository testRepository
    ) {
        this.visitRepository = visitRepository;
        this.testRepository = testRepository;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('lab:read')")
    public String get() {
        return "GET:: lab controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('lab:create')")
    public String post() {
        return "POST:: lab controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('lab:update')")
    public String put() {
        return "PUT:: lab controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('lab:delete')")
    public String delete() {
        return "DELETE:: lab controller";
    }


    //  the lab id will store in visit
    @ResponseBody
    @PostMapping("/{visitId}/tests")
    @PreAuthorize("hasAuthority('lab:create')")
    public Test createTest(
            @RequestBody Test test,
            @PathVariable Integer visitId
    ) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
        visit.setTest(test);

        return testRepository.save(test);
    }

}
