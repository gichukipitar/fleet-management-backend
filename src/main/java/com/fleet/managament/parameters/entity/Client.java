package com.fleet.managament.parameters.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fleet.managament.parameters.entity.County;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long clientId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;

//    @ManyToOne
//    @JoinColumn (name = "countryId", insertable=false, updatable=false)
//    private Country country;
//    private Long countryId;

//    @ManyToOne
//    @JoinColumn(name = "countyId", insertable=false, updatable=false)
//    private County county;
//    private Long countyId;
//    @Column(name = "details")
//    private String details;

}
