package com.example.hospitalManagement.HMS.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String description;
    private String names;
    private String folder_url;
    private Date add_date;
    private Date result_date;

    @OneToMany(mappedBy = "test")
    private Set<Visit> visits;


}