package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Controller.Visit.VisitDetailsRequest;
import com.example.hospitalManagement.HMS.Controller.Visit.VisitRequest;
import com.example.hospitalManagement.HMS.Domain.*;
import com.example.hospitalManagement.HMS.Domain.user.Role;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.*;
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

//    // create !patient user
//    @ResponseBody
//    @PostMapping("/new-user")
//    public User createNewUser(@RequestBody User user){
//        if (user.getRole() == Role.PATIENT) {
//            throw new IllegalArgumentException("Invalid role. 'PATIENT' role is not allowed for this endpoint.");
//        }
//        return userRepository.save(user);
//
//    }


    // create patient ===== anyone
//    @ResponseBody
//    @PutMapping("/patient/info")
//    public Patient createPatientInfo(@RequestBody Patient patient){
////        if (patient.getRole() != Role.PATIENT) {
////            throw new IllegalArgumentException("Invalid role. 'PATIENT' role is required for this endpoint.");
////        }
//        return patientRepository.save(patient);
//    }



        // works only for patient
        //    @ResponseBody
        //    @GetMapping("/profile/{id}")
        //    public Optional<Patient> getPatientProfileById(@PathVariable Integer id) {
        //        return patientRepository.findById(id);
        //    }


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

//    //update user info
//    @ResponseBody
//    @PutMapping("/profile/{id}")
//    public Object updateProfileInfo(
//            @PathVariable Integer id,
//            @RequestBody User user
//    ) {
//
//        return userRepository.save(user);
//        }
//

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







//    // the visit will create by doctor/Patient ,and the visit table will contain doctor,Patient id only
//    @ResponseBody
//    @PostMapping("/create/visit")
//    public Visit createVisit(@RequestBody VisitRequest visitRequest) {
//
//        User patient = patientRepository.findById(visitRequest.getPatient_id())
//                .orElseThrow(() -> new IllegalArgumentException("Patient not found."));
//        User doctor = userRepository.findById(visitRequest.getDoctor_id())
//                .orElseThrow(() -> new IllegalArgumentException("Doctor not found."));
//
//        Visit visit = new Visit();
//        visit.setPatient(patient);
//        visit.setDoctor(doctor);
//
//        return visitRepository.save(visit);
//    }

      // the XRay lab id will store in visit
//    // then the patient will go to xray, test and will go back to doctor
//    @ResponseBody
//    @PostMapping("/{visitId}/x-rays")
//    public XRay createXRay(
//            @RequestBody XRay xray,
//            @PathVariable Integer visitId
//    ) {
//        Visit visit = visitRepository.findById(visitId)
//                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
//        visit.setX_ray_image(xray);
//        return xRayRepository.save(xray);
//    }

//    //  the lab id will store in visit
//    @ResponseBody
//    @PostMapping("/{visitId}/tests")
//    public Test createTest(
//            @RequestBody Test test,
//            @PathVariable Integer visitId
//    ) {
//        Visit visit = visitRepository.findById(visitId)
//                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
//        visit.setTest(test);
//
//        return testRepository.save(test);
//    }


//    // then the doctor will modify the visit based on the result
//    @ResponseBody
//    @PutMapping("/visits/{visitId}")
//    public Visit updateVisitDetails(
//            @PathVariable Integer visitId,
//            @RequestBody VisitDetailsRequest visitDetailsRequest
//    ) {
//        Visit visit = visitRepository.findById(visitId)
//                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
//
//        visit.setSummary(visitDetailsRequest.getSummary());
//        visit.setDescription(visitDetailsRequest.getDescription());
//        visit.setPrescription(visitDetailsRequest.getPrescription());
//        visit.setTreatment(visitDetailsRequest.getTreatment());
//
//        return visitRepository.save(visit);
//    }

//     {pharmacistId}/{visitId}/medicines
////     the pharmacist will give the patient medicines
//    @ResponseBody
//    @PostMapping("/{visitId}/medicines")
//    public Medicine createMedicine(
//            @RequestBody Medicine medicine,
//            @PathVariable Integer visitId
//    ) {
//        Visit visit = visitRepository.findById(visitId)
//                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
//        visit.setMedicine(medicine);
////        visit.setPharmacist(pharmacist);
//        return medicineRepository.save(medicine);
//    }

// based on id for all

    @ResponseBody
    @GetMapping("/visit-details/{visitId}")
    public Visit getAllDoctors(@PathVariable Integer visitId) {
        return visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));

    }




}

