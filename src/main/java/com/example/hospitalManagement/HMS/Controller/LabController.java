package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Domain.Test;
import com.example.hospitalManagement.HMS.Domain.Visit;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{labID}/lab")
@PreAuthorize("hasRole('LAB')")
public class LabController {
    private final VisitRepository visitRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public LabController(
            VisitRepository visitRepository,
            TestRepository testRepository,
            UserRepository userRepository) {
        this.visitRepository = visitRepository;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
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
            @PathVariable Integer visitId,
            @PathVariable int labID
    ) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
        User lab = userRepository.findById(labID)
                .orElseThrow(() -> new IllegalArgumentException("Lab not found."));
        visit.setTest(test);

        visit.setLab(lab);

        return testRepository.save(test);
    }

}
