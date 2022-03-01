package com.pragma.persistence.adapter;

import com.pragma.entity.ClientEntity;
import lombok.RequiredArgsConstructor;
import com.pragma.mapper.ClientEntityMapper;
import com.pragma.model.Client;
import com.pragma.persistence.dao.IClientRepository;
import com.pragma.ports.IClientOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImp implements IClientOutputPort {

    private final IClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;

    @Override
    public List<Client> findAll() {
        return clientEntityMapper.toClients(clientRepository.findAll());
    }

    @Override
    public List<Client> findAllByIdNumberIn(Iterable<Long> idNumbers) {
        return clientEntityMapper.toClients(clientRepository.findAllByIdNumberIn(idNumbers));
    }

    @Override
    public List<Client> findByAgeGreaterThan(Integer age) {
        return clientEntityMapper.toClients(clientRepository.findByAgeGreaterThan(age));
    }

    @Override
    public Optional<Client> findFirstByIdTypeAndIdNumber(String idType, Long idNumber) {
        Optional<ClientEntity> optionalClientEntity = clientRepository.findFirstByIdTypeAndIdNumber(idType, idNumber);
        return optionalClientEntity.map(clientEntityMapper::toClient);
    }

    @Override
    public void save(Client newClient) {
        clientRepository.save(clientEntityMapper.toEntity(newClient));
    }

    @Override
    public void deleteByClientId(Long clientId) {
        clientRepository.deleteByClientId(clientId);
    }
}
