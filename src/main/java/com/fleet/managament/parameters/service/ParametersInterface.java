package com.fleet.managament.parameters.service;

import com.fleet.managament.parameters.dtos.ClientRequest;
import com.fleet.managament.utils.RestResponse;

public interface ParametersInterface {
    RestResponse createClient(ClientRequest clientRequest);
}
