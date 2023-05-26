package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.DTO.PatientProfileDTO;
import com.example.hospitalManagement.HMS.DTO.UserDTO;
import com.example.hospitalManagement.HMS.DTO.VisitDTO;
import com.example.hospitalManagement.HMS.Domain.*;
import com.example.hospitalManagement.HMS.Domain.user.Role;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.*;
import com.example.hospitalManagement.HMS.service.VisitService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class HomeController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final XRayRepository xRayRepository;
    private final TestRepository testRepository;
    private final MedicineRepository medicineRepository;
    private final VisitService visitService;

    public HomeController(
            UserRepository userRepository,
            PatientRepository patientRepository,
            VisitRepository visitRepository,
            XRayRepository xRayRepository,
            TestRepository testRepository,
            MedicineRepository medicineRepository,
            VisitService visitService) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.xRayRepository = xRayRepository;
        this.testRepository = testRepository;
        this.medicineRepository = medicineRepository;
        this.visitService = visitService;
    }

    @ResponseBody
    @GetMapping("/profile/{id}")
    public UserDTO getProfileById(@PathVariable Integer id) {

        User user = userRepository.findById(id)
                .orElse(null);
        if (user == null) {
            System.out.println("user not found!");
        }
        if (user.getRole() == Role.PATIENT) {

            Patient patient = patientRepository.findById(id).orElseThrow();

            PatientProfileDTO patientProfileDTO = new PatientProfileDTO();
            BeanUtils.copyProperties(user, patientProfileDTO, "password", "tokens", "enabled", "authorities",
                    "accountNonLocked", "credentialsNonExpired", "accountNonExpired");

            Set<VisitDTO> visitDTOs = patient.getVisits().stream()
                    .map(visitService::convertToVisitDTO)
                    .collect(Collectors.toSet());

            patientProfileDTO.setVisits(visitDTOs);

            return patientProfileDTO;

        } else {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO, "password", "tokens", "enabled", "authorities",
                    "accountNonLocked", "credentialsNonExpired", "accountNonExpired");

            return userDTO;
        }
    }

    // get all doctors
    @ResponseBody
    @GetMapping("/all/doctors")
    public List<UserDTO> getAllDoctors() {
        List<User> doctors = userRepository.findAllByRole(Role.DOCTOR);
        List<UserDTO> doctorDTOs = new ArrayList<>();

        for (User doctor : doctors) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(doctor, dto, "password", "tokens", "enabled", "authorities",
                    "accountNonLocked", "credentialsNonExpired", "accountNonExpired");
            doctorDTOs.add(dto);
        }

        return doctorDTOs;
    }

    // get all labs
    @ResponseBody
    @GetMapping("/all/labs")
    public List<UserDTO> getAllLabs() {
        List<User> labs = userRepository.findAllByRole(Role.LAB);
        List<UserDTO> labDTOs = new ArrayList<>();

        for (User lab : labs) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(lab, dto, "password", "tokens", "enabled", "authorities",
                    "accountNonLocked", "credentialsNonExpired", "accountNonExpired");
            labDTOs.add(dto);
        }

        return labDTOs;
    }
    // get all pharmacists
    @ResponseBody
    @GetMapping("/all/pharmacists")
    public List<UserDTO> getAllPharmacists() {
        List<User> pharmacists = userRepository.findAllByRole(Role.PHARMACIST);
        List<UserDTO> pharmacistDTOs = new ArrayList<>();

        for (User pharmacist : pharmacists) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(pharmacist, dto, "password", "tokens", "enabled", "authorities",
                    "accountNonLocked", "credentialsNonExpired", "accountNonExpired");
            pharmacistDTOs.add(dto);
        }

        return pharmacistDTOs;
    }



    // get all x-rays
    @ResponseBody
    @GetMapping("/all/x-rays")
    public List<UserDTO> getAllXRays() {


        List<User> xrays = userRepository.findAllByRole(Role.XRAY);
        List<UserDTO> xrayDTOs = new ArrayList<>();

        for (User xray : xrays) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(xray, dto, "password", "tokens", "enabled", "authorities",
                    "accountNonLocked", "credentialsNonExpired", "accountNonExpired");
            xrayDTOs.add(dto);
        }

        return xrayDTOs;
    }





}

