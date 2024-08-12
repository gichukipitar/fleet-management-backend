package com.fleet.managament.parameters.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryRequest {
    private String countryCode;
    private String countryName;
    private String capitalCity;
    private String description;
    private String nationality;
    private String continent;

}
