package com.fleet.managament.uac.mapper;

import com.fleet.managament.security.entity.User;
import com.fleet.managament.utils.StringTrimmer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
uses = StringTrimmer.class)
public interface UacMapper {
    UacMapper INSTANCE = Mappers.getMapper(UacMapper.class);
    User signUpToUserMapper(SignUpRequest signUpRequest);
    User signUpDateToUserMapper (SignUpUpdateRequest signUpUpdateRequest);
}
