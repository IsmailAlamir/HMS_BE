package com.example.hospitalManagement.HMS.auth;

import com.example.hospitalManagement.HMS.Domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String location;
    private String phone;


    private String email;

    private String password;

    @Builder.Default
    private Role role = Role.PATIENT;


}
