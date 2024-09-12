package com.fleet.managament.parameters.service;

import com.fleet.managament.parameters.dtos.ClientRequest;
import com.fleet.managament.parameters.dtos.ClientResponse;
import com.fleet.managament.parameters.entity.Client;
import com.fleet.managament.parameters.mapper.ClientMapper;
import com.fleet.managament.parameters.repository.ClientRepository;
import com.fleet.managament.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.fleet.managament.utils.UtilFunctions.validateNotNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientInterface {
    private final ClientRepository clientRepository;
    private final CustomRepositoryImpl customRepositoryImpl;
    private final CustomAppRepository <Client> customAppRepository;

    @Override
    public RestResponse createClient(ClientRequest clientRequest) {
        List<ErrorMessage> validateObj = validateNotNull(clientRequest);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            // Log the ClientRequest DTO before mapping
            log.info("ClientRequest received: {}", clientRequest);
            Client clientConfigs =
                    ClientMapper.INSTANCE.clientToEntity(clientRequest);
            // Log the mapped Client entity
            log.info("Mapped Client entity: {}", clientConfigs);
            Client savedConfigs = clientRepository.save(clientConfigs);
            // Log the saved Client entity
            log.info("Saved Client entity: {}", savedConfigs);
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

    @Override
    public RestResponse updateClient(Long clientId, ClientRequest clientRequest) {
        List<ErrorMessage> validateObj = validateNotNull(clientRequest);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("invalid fields").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Client> existingConfigOptional = clientRepository.findById(clientId);
        if (existingConfigOptional.isEmpty()) {
            return new RestResponse(
                    RestResponseObject.builder().message("client not found").build(),
                    HttpStatus.BAD_REQUEST);
        }
        //update existing fields with dto data
        Client existingClient = existingConfigOptional.get();
        ClientMapper.INSTANCE.updateClientFromDto(clientRequest, existingClient);

        try {
            Client savedClient = clientRepository.save(existingClient);
            ClientResponse clientResponse = ClientMapper.INSTANCE.clientToDto(savedClient);
            return new RestResponse(
                    RestResponseObject.builder().message("Client updated successfully").payload(clientResponse).build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating client{}", e.getMessage());
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public RestResponse fetchClient(SearchDto searchDto){
        List<ErrorMessage> validateObj = validateNotNull(searchDto);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            List<Client> configs = customAppRepository.findByFieldAndValue(
                    Client.class, searchDto.getFieldName(), searchDto.getSearchValue());
            List <ClientResponse> response =
                    ClientMapper.INSTANCE.clientsConfigsToClientResponseList(configs);

            return new RestResponse(
                    RestResponseObject.builder().message("Client fetched succesfully").payload(response).build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching client{}", e.getMessage());
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
