package com.fleet.managament.account.service;

import com.fleet.managament.account.dtos.InvoiceRequest;
import com.fleet.managament.account.dtos.InvoiceResponse;
import com.fleet.managament.account.entity.Invoice;
import com.fleet.managament.account.mapper.InvoiceMapper;
import com.fleet.managament.account.repositories.InvoiceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fleet.managament.utils.*;

import java.util.List;

import static com.fleet.managament.utils.UtilFunctions.validateNotNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
   private final InvoiceRepository invoiceRepository;

    @Override
    public RestResponse createInvoice(InvoiceRequest invoiceDTO) {
        List <ErrorMessage> validateObj = validateNotNull(invoiceDTO);
        if (ObjectUtils.isNotEmpty(validateObj)){
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid Fields!!").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            Invoice invoiceConfigs =
                    InvoiceMapper.INSTANCE.invoiceRequestToInvoiceConfigs(invoiceDTO);
            Invoice savedConfigs = invoiceRepository.save(invoiceConfigs);

            InvoiceResponse invoiceResponse =
                    InvoiceMapper.INSTANCE.invoiceConfigsToInvoiceResponse(savedConfigs);
            return new RestResponse(
                    RestResponseObject.builder().message("Invoice created successfully").payload(invoiceResponse).build(),
                    HttpStatus.OK);
        } catch (Exception e){
            log.error("createInvoice failed{}", e.getMessage());
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
