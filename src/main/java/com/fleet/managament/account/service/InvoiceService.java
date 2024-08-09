package com.fleet.managament.account.service;

import com.fleet.managament.account.dtos.InvoiceRequest;
import com.fleet.managament.utils.RestResponse;

public interface InvoiceService {
    RestResponse createInvoice(InvoiceRequest invoiceDTO);
}
