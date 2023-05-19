package com.example.hospitalManagement.HMS.repository;

import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<Patient> findById(Integer id);


}
