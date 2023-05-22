package com.example.hospitalManagement.HMS.Controller.Visit;

import lombok.Data;

@Data
public class VisitDetailsRequest {
    private String summary;
    private String description;
    private Integer prescription;
    private String treatment;

}
