package com.fleet.managament.parameters.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countyId;
    private String name;
    private String capital;
    private String code;

    @ManyToOne
    @JoinColumn(name = "countryId", insertable = false, updatable = false)
    private Country country;
    private Long countryId;
    private String details;
}
