package com.pragma.mapper;

import com.pragma.entity.ClientEntity;
import com.pragma.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {

    ClientEntityMapper INSTANCE = Mappers.getMapper(ClientEntityMapper.class);
    Client toClient(ClientEntity clientEntity);
    ClientEntity toEntity(Client client);
    List<Client> toClients(List<ClientEntity> clientEntities);
    List<ClientEntity> toEntities(List<Client> clients);

}
