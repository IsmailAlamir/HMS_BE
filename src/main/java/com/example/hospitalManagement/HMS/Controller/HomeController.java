package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Controller.Visit.VisitDetailsRequest;
import com.example.hospitalManagement.HMS.Controller.Visit.VisitRequest;
import com.example.hospitalManagement.HMS.Domain.*;
import com.example.hospitalManagement.HMS.Domain.user.Role;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final XRayRepository xRayRepository;
    private final TestRepository testRepository;
    private final MedicineRepository medicineRepository;

    public HomeController(
            UserRepository userRepository,
            PatientRepository patientRepository,
            VisitRepository visitRepository,
            XRayRepository xRayRepository,
            TestRepository testRepository,
            MedicineRepository medicineRepository
    ) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.xRayRepository = xRayRepository;
        this.testRepository = testRepository;
        this.medicineRepository = medicineRepository;
    }


    // get profile by id (for all users)
    @ResponseBody
    @GetMapping("/profile/{id}")
    public Object getProfileById(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }
        if (user.getRole() == Role.PATIENT) {
            return patientRepository.findById(id).orElse(null);
        } else {
            return user;
        }
    }

    // get all doctors
    @ResponseBody
    @GetMapping("/all/doctors")
    public List<User> getAllDoctors() {
        return userRepository.findAllByRole(Role.DOCTOR);
    }

    // get all labs
    @ResponseBody
    @GetMapping("/all/labs")
    public List<User> getAllLabs() {
        return userRepository.findAllByRole(Role.LAB);
    }

    // get all pharmacists
    @ResponseBody
    @GetMapping("/all/pharmacists")
    public List<User> getAllPharmacists() {
        return userRepository.findAllByRole(Role.PHARMACIST);
    }

    // get all x-rays
    @ResponseBody
    @GetMapping("/all/x-rays")
    public List<User> getAllXRays() {
        return userRepository.findAllByRole(Role.XRAY);
    }


// based on id for all

    @ResponseBody
    @GetMapping("/visit-details/{visitId}")
    @PreAuthorize("hasAnyAuthority('doctor:read', 'patient:read')")
    public Visit getVisitDetails(@PathVariable Integer visitId, Authentication authentication) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));

        // Get the username of the authenticated user
        String username = authentication.getName();

        // Check if the authenticated user has the role of "ROLE_PATIENT" and is the owner of the visit
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"))) {
            User patient = visit.getPatient();
            if (!patient.getUsername().equals(username)) {
                throw new AccessDeniedException("You are not authorized to access this visit.");
            }
        }


        return visit;
    }





}

