package com.example.hospitalManagement.HMS.Domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.hospitalManagement.HMS.Domain.user.Permission.*;

@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    DOCTOR_READ,
                    DOCTOR_UPDATE,
                    DOCTOR_CREATE,
                    DOCTOR_DELETE,
                    LAB_READ,
                    LAB_UPDATE,
                    LAB_CREATE,
                    LAB_DELETE,
                    PATIENT_READ,
                    PATIENT_UPDATE,
                    PATIENT_CREATE,
                    PATIENT_DELETE,
                    PHARMACIST_READ,
                    PHARMACIST_UPDATE,
                    PHARMACIST_CREATE,
                    PHARMACIST_DELETE,
                    XRAY_READ,
                    XRAY_UPDATE,
                    XRAY_CREATE,
                    XRAY_DELETE
            )
    ),
    DOCTOR(
            Set.of(
                    DOCTOR_READ,
                    DOCTOR_UPDATE,
                    DOCTOR_CREATE,
                    DOCTOR_DELETE,
                    PATIENT_READ,
                    PATIENT_UPDATE,
                    PATIENT_CREATE,
                    PATIENT_DELETE

                    )
    ),
    PATIENT(
            Set.of(
                    PATIENT_READ,
                    PATIENT_UPDATE,
                    PATIENT_CREATE,
                    PATIENT_DELETE
            )
    ),
    PHARMACIST(
            Set.of(
                    PHARMACIST_READ,
                    PHARMACIST_UPDATE,
                    PHARMACIST_CREATE,
                    PHARMACIST_DELETE
            )
    ),
    LAB(
            Set.of(
                    LAB_READ,
                    LAB_UPDATE,
                    LAB_CREATE,
                    LAB_DELETE
            )
    ),
    XRAY(
            Set.of(
                    XRAY_READ,
                    XRAY_UPDATE,
                    XRAY_CREATE,
                    XRAY_DELETE
            )
    )



    ;

    @Getter
    private final Set<Permission> permissions;
    
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
