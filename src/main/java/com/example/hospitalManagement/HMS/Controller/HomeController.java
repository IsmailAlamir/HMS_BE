package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.Domain.User;
import com.example.hospitalManagement.HMS.repository.PatientRepository;
import com.example.hospitalManagement.HMS.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public HomeController(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @ResponseBody
    @PostMapping("/new-user")
    public User createNewUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @ResponseBody
    @PostMapping("/patient-info")
    public Patient createPatientInfo(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }


    @ResponseBody
    @GetMapping("/profile/{id}")
    public Optional<Patient> getPatientProfileById(@PathVariable Integer id) {
        return patientRepository.findById(id);
    }
    
}

