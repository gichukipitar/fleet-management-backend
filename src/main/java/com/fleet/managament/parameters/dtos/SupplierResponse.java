package com.fleet.managament.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private String email;
}
