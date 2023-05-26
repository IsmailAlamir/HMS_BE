package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.DTO.VisitDTO;
import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.Domain.Visit;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/{patientID}/patient")
@PreAuthorize("hasRole('PATIENT')")
public class PatientController {
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;


    public PatientController(
            PatientRepository patientRepository,
            VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
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
    @PutMapping("/info")
    @PreAuthorize("hasAuthority('patient:update')")
    public String updatePatientInfo(@PathVariable("patientID") Integer id, @RequestBody Patient updatedPatient) {
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

    // based on id for all
    @ResponseBody
    @GetMapping("/visit-details/{visitId}")
    @PreAuthorize("hasAuthority('patient:read')")
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

