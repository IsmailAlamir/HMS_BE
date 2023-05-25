package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Domain.Medicine;
import com.example.hospitalManagement.HMS.Domain.Visit;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{pharmacistID}/pharmacist")
@PreAuthorize("hasRole('PHARMACIST')")
public class PharmacistController {

    private final VisitRepository visitRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;

    public PharmacistController(
            VisitRepository visitRepository,
            MedicineRepository medicineRepository,
            UserRepository userRepository) {
        this.medicineRepository = medicineRepository;
        this.visitRepository = visitRepository;

        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('pharmacist:read')")
    public String get() {
        return "GET:: pharmacist controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('pharmacist:create')")
    public String post() {
        return "POST:: pharmacist controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('pharmacist:update')")
    public String put() {
        return "PUT:: pharmacist controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('pharmacist:delete')")
    public String delete() {
        return "DELETE:: pharmacist controller";
    }

    //     the pharmacist will give the patient medicines
    @ResponseBody
    @PostMapping("/{visitId}/medicines")
    @PreAuthorize("hasAuthority('pharmacist:create')")
    public Medicine createMedicine(
            @RequestBody Medicine medicine,
            @PathVariable Integer visitId,
            @PathVariable int pharmacistID
    ) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
        User pharmacist = userRepository.findById(pharmacistID)
                .orElseThrow(() -> new IllegalArgumentException("Pharmacist not found."));

        visit.setMedicine(medicine);
        visit.setPharmacist(pharmacist);

        return medicineRepository.save(medicine);

}

}
