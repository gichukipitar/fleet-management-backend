package com.fleet.managament.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponse extends ResponseEntity<RestResponseObject> {
    public RestResponse(RestResponseObject body, HttpStatus statusCode) {
        super(body, statusCode);
    }
}
