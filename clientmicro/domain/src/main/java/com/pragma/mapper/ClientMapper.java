package com.pragma.mapper;

import com.pragma.dto.ClientDto;
import com.pragma.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client toClient(ClientDto clientDto);
    ClientDto toDto(Client client);
    List<Client> toClients(List<ClientDto> clientDtos);
    List<ClientDto> toDtos(List<Client> clients);

}
