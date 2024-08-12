package com.fleet.managament.parameters.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fleet.managament.parameters.entity.Country;
import com.fleet.managament.parameters.entity.County;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationRequest {
    private String description;
    private String details;
    private Country country;
    private County county;
    private String city;
    private String address;
}
