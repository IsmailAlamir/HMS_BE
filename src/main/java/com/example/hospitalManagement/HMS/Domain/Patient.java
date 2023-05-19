package com.example.hospitalManagement.HMS.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "user_id") //establishing the one-to-one relationship between the User and Patient entities.
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends User{


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
