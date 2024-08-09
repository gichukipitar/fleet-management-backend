package com.fleet.managament.account.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
@ToString
public class InvoiceRequest implements Serializable {
    //Long id;
    private Date invoiceDate;
    private Long invoiceStatusId;
    private Long clientId;
    @NotNull(message = "remarks cannot be null")
    private String remarks;
}