package com.example.hospitalManagement.HMS.Domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    DOCTOR_READ("doctor:read"),
    DOCTOR_UPDATE("doctor:update"),
    DOCTOR_CREATE("doctor:create"),
    DOCTOR_DELETE("doctor:delete"),

    LAB_READ("lab:read"),
    LAB_UPDATE("lab:update"),
    LAB_CREATE("lab:create"),
    LAB_DELETE("lab:delete"),

    PATIENT_READ("patient:read"),
    PATIENT_UPDATE("patient:update"),
    PATIENT_CREATE("patient:create"),
    PATIENT_DELETE("patient:delete"),

    PHARMACIST_READ("pharmacist:read"),
    PHARMACIST_UPDATE("pharmacist:update"),
    PHARMACIST_CREATE("pharmacist:create"),
    PHARMACIST_DELETE("pharmacist:delete"),

    XRAY_READ("xray:read"),
    XRAY_UPDATE("xray:update"),
    XRAY_CREATE("xray:create"),
    XRAY_DELETE("xray:delete"),
    ;

    @Getter
    private final String permission;


}