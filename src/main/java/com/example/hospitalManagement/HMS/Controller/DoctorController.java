package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Controller.Visit.VisitDetailsRequest;
import com.example.hospitalManagement.HMS.Controller.Visit.VisitRequest;
import com.example.hospitalManagement.HMS.Domain.Visit;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{doctorID}/doctor")
@PreAuthorize("hasRole('DOCTOR')")
public class DoctorController {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;

    public DoctorController(
            UserRepository userRepository,
            PatientRepository
                    patientRepository,
            VisitRepository visitRepository
    ) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('doctor:read')")
    public String get() {
        return "GET:: doctor controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('doctor:create')")
    public String post() {
        return "POST:: doctor controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('doctor:update')")
    public String put() {
        return "PUT:: doctor controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('doctor:delete')")
    public String delete() {
        return "DELETE:: doctor controller";
    }


        // the visit will create by doctor/Patient ,and the visit table will contain doctor,Patient id only
    @ResponseBody
    @PostMapping("/create/visit")
    @PreAuthorize("hasAuthority('doctor:create')")
    public String createVisit(@RequestBody VisitRequest visitRequest , @PathVariable int doctorID) {
        User patient = patientRepository.findById(visitRequest.getPatient_id())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found."));
        User doctor = userRepository.findById(doctorID)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found."));

        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visitRepository.save(visit);

        return "visit created";
    }


    // then the doctor will modify the visit based on the result
    @ResponseBody
    @PutMapping("/visits/{visitId}")
    @PreAuthorize("hasAuthority('doctor:update')")
    public String updateVisitDetails(
            @PathVariable int visitId,
            @RequestBody VisitDetailsRequest visitDetailsRequest
    ) {
        System.out.println(visitDetailsRequest);
        System.out.println(visitId);
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));

        visit.setSummary(visitDetailsRequest.getSummary());
        visit.setDescription(visitDetailsRequest.getDescription());
        visit.setPrescription(visitDetailsRequest.getPrescription());
        visit.setTreatment(visitDetailsRequest.getTreatment());
        visitRepository.save(visit);
        return "Done";
    }







}
