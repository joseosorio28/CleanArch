package com.pragma.ports;

import com.pragma.dto.ClientDto;

import java.util.List;

public interface IClientInputPort {
    List<ClientDto> getClients();
    List<ClientDto> getClientsByAge(Integer age);
    ClientDto searchClient(String idType, Long idNumber);
    void addClient(ClientDto newClientDto);
    void updateClientReview(ClientDto clientDto, String idTypeRequest, Long idNumberRequest);
    void deleteClient(String idType, Long idNumber);
}
