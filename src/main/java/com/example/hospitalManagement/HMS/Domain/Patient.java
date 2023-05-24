package com.example.hospitalManagement.HMS.Domain;


import com.example.hospitalManagement.HMS.Domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Builder(builderMethodName = "patientBuilder")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "user_id") //establishing the one-to-one relationship between the User and Patient entities.
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends User {


    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    private Double height;
    private Double weight;
    private String allergies;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;


}

