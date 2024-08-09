package com.fleet.managament.account.mapper;

import com.fleet.managament.account.dtos.InvoiceRequest;
import com.fleet.managament.account.dtos.InvoiceResponse;
import com.fleet.managament.account.entity.Invoice;
import com.fleet.managament.utils.StringTrimmer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = StringTrimmer.class)
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);


    Invoice invoiceRequestToInvoiceConfigs(InvoiceRequest invoiceRequest);
    InvoiceResponse invoiceConfigsToInvoiceResponse(Invoice invoice);
}
