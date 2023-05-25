package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.auth.AuthenticationResponse;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.http.ResponseEntity;
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
    public String updatePatientInfo(@PathVariable("id") Integer id, @RequestBody Patient updatedPatient) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow();

        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setLocation(updatedPatient.getLocation());
        patient.setPhone(updatedPatient.getPhone());
        patient.setBirthday(updatedPatient.getBirthday());
        patient.setGender(updatedPatient.getGender());
        patient.setBloodType(updatedPatient.getBloodType());
        patient.setHeight(updatedPatient.getHeight());
        patient.setWeight(updatedPatient.getWeight());
        patient.setAllergies(updatedPatient.getAllergies());

        patient.setEmail(patient.getEmail());
        patient.setUsername(patient.getUsername());
        patientRepository.save(patient);
        return "Info updated";
    }


}

