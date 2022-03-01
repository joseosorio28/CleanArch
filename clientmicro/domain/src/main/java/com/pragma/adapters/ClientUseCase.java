package com.pragma.adapters;

import com.pragma.dto.ClientDto;
import com.pragma.exceptions.*;
import com.pragma.mapper.ClientMapper;
import com.pragma.model.Client;
import com.pragma.ports.IClientInputPort;
import com.pragma.ports.IClientOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientUseCase implements IClientInputPort {

    private final IClientOutputPort clientOutputPort;
    private final ClientMapper clientMapper;

    private static final int MAX_AGE = 100;
    private static final int MIN_AGE = 1;

    @Override
    public List<ClientDto> getClients() {
        List<Client> clients = clientOutputPort.findAll();
        return clientMapper.toDtos(clients);
    }

    @Override
    public List<ClientDto> getClientsByAge(Integer age) {
        if (age >= MIN_AGE && age <= MAX_AGE) {
            List<Client> clients = clientOutputPort.findByAgeGreaterThan(age);
            if (clients.isEmpty()) {
                throw new ClientByAgeNotFoundException(age);
            }
            return clientMapper.toDtos(clients);
        } else {
            throw new ClientSearchAgeException();
        }
    }

    @Override
    public ClientDto searchClient(String idType, Long idNumber) {
        return clientMapper.toDto(
                clientOutputPort
                        .findFirstByIdTypeAndIdNumber(idType, idNumber)
                        .orElseThrow(() -> new ClientNotFoundException(idType, idNumber)));
    }

    @Override
    public void addClient(ClientDto newClientDto) {
        Client newClient = clientMapper.toClient(newClientDto);
        clientOutputPort
                .findFirstByIdTypeAndIdNumber(
                        newClient.getIdType(),
                        newClient.getIdNumber())
                .ifPresentOrElse(
                        client -> {
                            throw new ClientFoundException(
                                    client.getIdType(),
                                    client.getIdNumber());
                        },
                        () ->
                                clientOutputPort.save(newClient));
    }

    @Override
    public void updateClientReview(ClientDto clientDto, String idTypeRequest, Long idNumberRequest) {
        String idTypeInJson = clientDto.getIdType();
        Long idNumberInJson = clientDto.getIdNumber();
        List<Client> clients = clientOutputPort.findAllByIdNumberIn(Arrays.asList(idNumberRequest, idNumberInJson));

        Optional<Client> clientFoundByRequest = searchInClientList(clients, idTypeRequest, idNumberRequest);
        boolean isClientPresentByRequestParam = clientFoundByRequest.isPresent();
        Optional<Client> clientFoundByJson = searchInClientList(clients, idTypeInJson, idNumberInJson);
        boolean isClientPresentByJsonData = clientFoundByJson.isPresent();

        if (!isClientPresentByJsonData && isClientPresentByRequestParam) {//Client present in DB so update it
            updateClientAndImage(clientDto, clientFoundByRequest.get(), idTypeRequest, idNumberRequest);
        } else if (!isClientPresentByJsonData) {
            clientOutputPort.save(clientMapper.toClient(clientDto));//Client json/request not present then create new client
            //updateImage(clientMapper.toImage(clientDto), idTypeRequest, idNumberRequest);
        } else if (isClientPresentByRequestParam) {
            if ((idTypeInJson + idNumberInJson).equals(idTypeRequest + idNumberRequest)) {//Client present in DB so update it
                updateClientAndImage(clientDto, clientFoundByRequest.get(), idTypeRequest, idNumberRequest);
            } else {
                throw new ClientUpdateException(idTypeRequest, idNumberRequest,
                        idTypeInJson, idNumberInJson);//Client present but update info already in DB
            }
        } else {
            throw new ClientNotFoundException(idTypeRequest, idNumberRequest);//Client requested to update not present in DB (json present)
        }
    }

    public Optional<Client> searchInClientList(List<Client> clients, String idType, Long idNumber) {
        return clients
                .stream()
                .filter(clientSearch ->
                        clientSearch.getIdType().equals(idType) &&
                                clientSearch.getIdNumber().equals(idNumber))
                .findFirst();
    }

    public void updateClientAndImage(ClientDto updatedClient, Client clientFound,
                                     String idType, Long idNumber) {
        updateClient(clientMapper.toClient(updatedClient), clientFound);
        //updateImage(clientMapper.toImage(updatedClient), idType, idNumber);
    }

    public void updateClient(Client clientInfo, Client presentClient) {
        presentClient.setFirstName(clientInfo.getFirstName());
        presentClient.setLastName(clientInfo.getLastName());
        presentClient.setIdType(clientInfo.getIdType());
        presentClient.setIdNumber(clientInfo.getIdNumber());
        presentClient.setAge(clientInfo.getAge());
        presentClient.setCityOfBirth(clientInfo.getCityOfBirth());
        clientOutputPort.save(presentClient);
    }

    @Override
    public void deleteClient(String idType, Long idNumber) {
        clientOutputPort
                .findFirstByIdTypeAndIdNumber(idType, idNumber)
                .ifPresentOrElse(
                        client -> clientOutputPort.deleteByClientId(client.getClientId()),
                        () -> {
                            throw new ClientNotFoundException(idType, idNumber);
                        });
    }
}
