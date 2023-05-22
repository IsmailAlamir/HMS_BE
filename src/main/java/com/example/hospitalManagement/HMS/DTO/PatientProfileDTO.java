package com.example.hospitalManagement.HMS.DTO;

import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.Domain.user.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientProfileDTO {
    private Patient patient;
    private User user;

    public PatientProfileDTO(Patient patient, User user) {
        this.patient = patient;
        this.user = user;
    }

}
