package com.pragma.service;

import com.pragma.adapters.ClientUseCase;
import com.pragma.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImp{

    private final ClientUseCase clientUseCase;

    public List<ClientDto> getClients() {
        return clientUseCase.getClients();
    }

    public List<ClientDto> getClientsByAge(Integer age) {
        return clientUseCase.getClientsByAge(age);
    }

    public ClientDto searchClient(String idType, Long idNumber) {
        return clientUseCase.searchClient(idType,idNumber);
    }

    @Transactional
    public void addClient(ClientDto clientDto) {
        clientUseCase.addClient(clientDto);
    }

    @Transactional
    public void updateClient(ClientDto clientDto, String idTypeRequest, Long idNumberRequest) {
        clientUseCase.updateClientReview(clientDto,idTypeRequest,idNumberRequest);
    }

    @Transactional
    public void deleteClient(String idType, Long idNumber) {
        clientUseCase.deleteClient(idType,idNumber);
    }
}
