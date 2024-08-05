package com.fleet.managament.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RestResponseObject <T> {
    private String message;
    private T payload;
    private List<ErrorMessage> errors;
}
