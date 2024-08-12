package com.fleet.managament.parameters.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String remarks;
}
