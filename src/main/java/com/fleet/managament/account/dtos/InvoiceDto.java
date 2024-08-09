package com.fleet.managament.account.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fleet.managament.account.entity.Invoice}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceDto implements Serializable {
    Long id;
    Date invoiceDate;
    Long invoiceStatusId;
    Long clientId;
    String remarks;
}