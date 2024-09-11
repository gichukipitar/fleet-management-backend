package com.fleet.managament.parameters.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private String email;

//    @ManyToOne
//    @JoinColumn(name="countryId", insertable=false, updatable=false)
//    private Country country;
//    private Long countryId;
//
//    @ManyToOne
//    @JoinColumn(name="countyId", insertable=false, updatable=false)
//    private County county;
//    private Long countyId;

    private String details;

}
