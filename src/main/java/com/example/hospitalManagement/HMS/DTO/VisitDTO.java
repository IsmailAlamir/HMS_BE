package com.example.hospitalManagement.HMS.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitDTO {

    private Integer id;
    private String summary;
    private String description;
    private String treatment;
    private Integer prescription;
    private Integer patientId;
    private Integer doctorId;
    private Integer pharmacistId;
    private Integer labId;
    private Integer xrayId;
    private Integer medicineId;
    private Integer testId;
    private Integer xRayImageId;

}
