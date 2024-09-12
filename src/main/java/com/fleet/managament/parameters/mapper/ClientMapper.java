package com.fleet.managament.parameters.mapper;

import com.fleet.managament.parameters.dtos.ClientRequest;
import com.fleet.managament.parameters.dtos.ClientResponse;
import com.fleet.managament.parameters.entity.Client;
import com.fleet.managament.utils.StringTrimmer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = StringTrimmer.class)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "clientId", ignore = true) // Ignore clientId to ensure it's generated by the database
    Client clientToEntity(ClientRequest clientRequest);

    ClientResponse clientToDto(Client client);

    @Mapping(target = "clientId", ignore = true) // Ignore clientId to prevent overwriting it
    void updateClientFromDto(ClientRequest clientRequest, @MappingTarget Client client);

    List <ClientResponse> clientsConfigsToClientResponseList(List<Client> clientConfigs);

}
