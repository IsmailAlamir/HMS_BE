package com.example.hospitalManagement.HMS.repository;

import com.example.hospitalManagement.HMS.Domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
}
