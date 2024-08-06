package com.fleet.managament.uac.dtos;

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
public class ChangePasswordRequest {
    @NotNull (message = "UserName cannot be null")
    @Pattern(
            regexp = "^(0\\d{9}|[a-zA-Z0-9_]+(?:\\.[a-zA-Z0-9_]+)*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
            message = "Provide valid phone or email")
    private String userName;
    @NotNull(message = "Password cannot be null")
    private String password;
}
