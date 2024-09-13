package com.fleet.managament.parameters.service;

import com.fleet.managament.parameters.dtos.ClientRequest;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.SearchDto;
import org.springframework.data.domain.Pageable;

public interface ClientInterface {
    RestResponse createClient(ClientRequest clientRequest);
    RestResponse updateClient(Long clientId, ClientRequest clientRequest);

    RestResponse fetchClient (SearchDto searchDto);

    RestResponse fetchPaginatedClients(SearchDto searchDto, Pageable pageable);
}
