package com.example.hospitalManagement.HMS.DTO;

import com.example.hospitalManagement.HMS.Domain.BloodType;
import com.example.hospitalManagement.HMS.Domain.Gender;
import com.example.hospitalManagement.HMS.Domain.Visit;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
public class PatientProfileDTO extends UserDTO{

    private Date birthday;
    private Gender gender;
    private BloodType bloodType;
    private Double height;
    private Double weight;
    private String allergies;

    private Set<VisitDTO> visits;



}
