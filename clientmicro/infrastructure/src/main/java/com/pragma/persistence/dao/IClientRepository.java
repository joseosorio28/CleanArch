package com.pragma.persistence.dao;

import com.pragma.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findAll();

    Optional<ClientEntity> findFirstByIdTypeAndIdNumber(String idType, Long idNumber);

    List<ClientEntity> findByAgeGreaterThan(Integer age);

    List<ClientEntity> findAllByIdNumberIn(Iterable<Long> idNumbers);

    void deleteByClientId(Long clientId);

}
