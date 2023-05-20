package com.example.hospitalManagement.HMS.Controller;

import lombok.Data;

@Data
public class VisitRequest {
    private Integer patient_id;
    private Integer doctor_id;
}
