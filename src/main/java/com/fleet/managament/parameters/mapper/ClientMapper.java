package com.fleet.managament.parameters.mapper;

import com.fleet.managament.parameters.dtos.ClientRequest;
import com.fleet.managament.parameters.entity.Client;
import com.fleet.managament.utils.StringTrimmer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = StringTrimmer.class)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client clientRegistrationRequest(ClientRequest clientRequest);
}
