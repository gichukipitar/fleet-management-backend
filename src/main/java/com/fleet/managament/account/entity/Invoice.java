package com.fleet.managament.account.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fleet.managament.parameters.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    @ManyToOne
    @JoinColumn(name = "invoiceStatusId", insertable = false, updatable = false)
    private InvoiceStatus invoiceStatus;
    private Long invoiceStatusId;
    @ManyToOne
    @JoinColumn(name = "clientId", insertable = false, updatable = false)
    private Client client;
    private Long clientId;
    private String remarks;
}
