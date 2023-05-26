package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Controller.Visit.VisitDetailsRequest;
import com.example.hospitalManagement.HMS.Controller.Visit.VisitRequest;
import com.example.hospitalManagement.HMS.DTO.VisitDTO;
import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.Domain.Visit;
import com.example.hospitalManagement.HMS.Domain.user.Role;
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


    @ResponseBody
    @GetMapping("/profile/{id}")
    public Object getProfileById(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }
        if (user.getRole() == Role.PATIENT) {
            System.out.println(id);
            System.out.println();

            Patient patient =patientRepository.findById(id).orElseThrow();
            return patient;
        } else {
            return user;
        }
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



    // based on id for all
    @ResponseBody
    @GetMapping("/visit-details/{visitId}")
    @PreAuthorize("hasAuthority('doctor:read')")
    public VisitDTO getVisitDetails(@PathVariable int visitId) {

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));

        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setId(visit.getId());
        visitDTO.setSummary(visit.getSummary());
        visitDTO.setDescription(visit.getDescription());
        visitDTO.setTreatment(visit.getTreatment());
        visitDTO.setPrescription(visit.getPrescription());
        visitDTO.setPatientId(visit.getPatient() != null ? visit.getPatient().getId() : null);
        visitDTO.setDoctorId(visit.getDoctor() != null ? visit.getDoctor().getId() : null);
        visitDTO.setPharmacistId(visit.getPharmacist() != null ? visit.getPharmacist().getId() : null);
        visitDTO.setLabId(visit.getLab() != null ? visit.getLab().getId() : null);
        visitDTO.setXrayId(visit.getXray() != null ? visit.getXray().getId() : null);
        visitDTO.setMedicineId(visit.getMedicine() != null ? visit.getMedicine().getId() : null);
        visitDTO.setTestId(visit.getTest() != null ? visit.getTest().getId() : null);
        visitDTO.setXRayImageId(visit.getX_ray_image() != null ? visit.getX_ray_image().getId() : null);

        return visitDTO;
    }


}
