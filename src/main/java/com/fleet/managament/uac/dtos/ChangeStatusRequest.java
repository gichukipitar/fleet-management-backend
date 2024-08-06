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
public class ChangeStatusRequest {

    @NotNull(message = "Login cannot be null")
    @Pattern(
            regexp = "^(0\\d{9}|[a-zA-Z0-9_]+(?:\\.[a-zA-Z0-9_]+)*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
            message = "Provide valid userName or email")
    private String userName;

    @NotNull(message = "status cannot be null")
    private boolean status;
}