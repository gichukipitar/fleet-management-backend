package com.fleet.managament.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountyResponse {
    private String name;
    private String capital;
    private String code;
}
