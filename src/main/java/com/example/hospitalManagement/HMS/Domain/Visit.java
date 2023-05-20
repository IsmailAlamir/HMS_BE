package com.example.hospitalManagement.HMS.Domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;



    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "pharmacist_id")
    private User pharmacist;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private User lab ;

    @ManyToOne
    @JoinColumn(name = "x_ray_id")
    private User xray;


    private String summary;
    private String description;
    private String treatment;
    private Integer prescription;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "x_rays_image_id")
    private XRay x_ray_image;


    public Visit(int id) {
        this.id = id;
    }


}
