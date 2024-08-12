package com.fleet.managament.parameters.dtos;

import com.fleet.managament.parameters.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private String description;
    private String details;
    private Country country;
    private County county;
    private String city;
    private String address;
}
