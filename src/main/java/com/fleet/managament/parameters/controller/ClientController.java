package com.fleet.managament.parameters.controller;

import com.fleet.managament.parameters.dtos.ClientRequest;
import com.fleet.managament.parameters.service.ClientServiceImpl;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.SearchDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor

public class ClientController {
    private final ClientServiceImpl clientServiceImpl;
    @PostMapping(path = "/create", produces = "application/json")
    public RestResponse createClient (@Valid @RequestBody ClientRequest clientRequest) {
        return clientServiceImpl.createClient(clientRequest);
    }
    @PostMapping(path = "/update", produces = "application/json")
    public RestResponse updateClient (@Valid @RequestParam(value = "clientId") Long clientId,
    @Valid @RequestBody ClientRequest clientRequest) {
        return clientServiceImpl.updateClient(clientId, clientRequest);
    }
    @GetMapping(path="/search/client", produces = "application/json")
    public RestResponse searchClient(SearchDto searchDto) {
        return clientServiceImpl.fetchClient(searchDto);
    }
}
