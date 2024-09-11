package com.fleet.managament.parameters.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fleet.managament.parameters.entity.County;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ClientRequest implements Serializable {
    @NotNull(message = "firstname cannot be null")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "phoneNumber cannot be null")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number format")
    private String phoneNumber;
    private String address;
    private String city;
//    private County county;
//    private Long countryId;
}
