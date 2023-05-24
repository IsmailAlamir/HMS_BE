package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patient")
@PreAuthorize("hasRole('PATIENT')")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(
            PatientRepository patientRepository
    ) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('patient:read')")
    public String get() {
        return "GET:: patient controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('patient:create')")
    public String post() {
        return "POST:: patient controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('patient:update')")
    public String put() {
        return "PUT:: patient controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('patient:delete')")
    public String delete() {
        return "DELETE:: patient controller";
    }


    @ResponseBody
    @PutMapping("{id}/info")
    @PreAuthorize("hasAuthority('patient:update')")
    public Patient updatePatientInfo(@PathVariable("id") Integer id, @RequestBody Patient updatedPatient) {
        updatedPatient.setId(id);
        return patientRepository.save(updatedPatient);
    }


}

