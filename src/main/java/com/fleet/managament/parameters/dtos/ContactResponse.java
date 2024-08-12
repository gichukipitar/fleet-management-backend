package com.fleet.managament.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String remarks;
}
