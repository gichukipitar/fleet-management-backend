package com.fleet.managament.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
    private String countryCode;
    private String countryName;
    private String capitalCity;
    private String description;
    private String nationality;
    private String continent;

}
