package com.fleet.managament.utils;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    @NotNull(message = "Field name cannot be null")
    private String fieldName;
    @NotNull(message = "search value cannot be null")
    private String searchValue;
}