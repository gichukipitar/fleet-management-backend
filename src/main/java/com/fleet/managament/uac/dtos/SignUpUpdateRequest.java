package com.fleet.managament.uac.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpUpdateRequest {

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @NotNull(message = "Login cannot be null")
    @Pattern(
            regexp = "^(0\\d{9}|[a-zA-Z0-9_]+(?:\\.[a-zA-Z0-9_]+)*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
            message = "Provide valid phone or email")
    private String userName;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "phone cannot be null")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number format")
    private String phone;

    @NotNull(message = "password cannot be null")
    private String password;

    @NotNull(message = "Role cannot be null")
    private String role;

}
