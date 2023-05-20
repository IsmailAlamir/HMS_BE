package com.example.hospitalManagement.HMS.Controller;

import lombok.Data;

@Data
public class VisitDetailsRequest {
    private String summary;
    private String description;
    private Integer prescription;
    private String treatment;

}
