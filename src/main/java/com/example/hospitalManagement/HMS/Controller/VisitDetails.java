package com.example.hospitalManagement.HMS.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VisitDetails {
    private Integer id;
    private String description;
    private Integer prescription;
    private String summary;
    private String treatment;
    private Integer doctor_id;
    private Integer lab_id;
    private Integer medicine_id;
    private Integer patient_id;
    private Integer pharmacist_id;
    private Integer test_id;
    private Integer x_rays_image_id;
    private Integer x_ray_id;


}