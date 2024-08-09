package com.fleet.managament.account.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fleet.managament.account.entity.Invoice}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceDTO implements Serializable {
    Long id;
    Date invoiceDate;
    Long invoiceStatusId;
    Long clientId;
    String remarks;
}