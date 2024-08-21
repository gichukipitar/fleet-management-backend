package com.fleet.managament.parameters.service;

import com.fleet.managament.parameters.dtos.ClientRequest;
import com.fleet.managament.parameters.dtos.ClientResponse;
import com.fleet.managament.parameters.entity.Client;
import com.fleet.managament.parameters.mapper.ClientMapper;
import com.fleet.managament.parameters.repository.ClientRepository;
import com.fleet.managament.utils.ErrorMessage;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.RestResponseObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fleet.managament.utils.UtilFunctions.validateNotNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientInterface {
    private final ClientRepository clientRepository;

    @Override
    public RestResponse createClient(ClientRequest clientRequest) {
        List<ErrorMessage> validateObj = validateNotNull(clientRequest);
        if (ObjectUtils.isNotEmpty(validateObj)){
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            Client clientConfigs =
                    ClientMapper.INSTANCE.clientToEntity(clientRequest);
            Client savedConfigs = clientRepository.save(clientConfigs);
            ClientResponse response = ClientMapper.INSTANCE.clientToDto(savedConfigs);
            return new RestResponse(
                    RestResponseObject.builder().message("created client successfully").payload(response).build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating client{}", e.getMessage());
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
