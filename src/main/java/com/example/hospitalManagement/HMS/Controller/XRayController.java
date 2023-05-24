package com.example.hospitalManagement.HMS.Controller;

import com.example.hospitalManagement.HMS.Domain.Visit;
import com.example.hospitalManagement.HMS.Domain.XRay;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/x-ray")
@PreAuthorize("hasRole('XRAY')")
public class XRayController {

    private final VisitRepository visitRepository;
    private final XRayRepository xRayRepository;

    public XRayController(
            VisitRepository visitRepository,
            XRayRepository xRayRepository
    ) {
        this.visitRepository = visitRepository;
        this.xRayRepository = xRayRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('xray:read')")
    public String get() {
        return "GET:: xray controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('xray:create')")
    public String post() {
        return "POST:: xray controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('xray:update')")
    public String put() {
        return "PUT:: xray controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('xray:delete')")
    public String delete() {
        return "DELETE:: xray controller";
    }

//     the XRay lab id will store in visit
    // then the patient will go to xray, test and will go back to doctor
    @ResponseBody
    @PostMapping("/{visitId}/x-rays")
    @PreAuthorize("hasAuthority('xray:create')")
    public XRay createXRay(
            @RequestBody XRay xray,
            @PathVariable Integer visitId
    ) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
        visit.setX_ray_image(xray);
        return xRayRepository.save(xray);
    }


}
