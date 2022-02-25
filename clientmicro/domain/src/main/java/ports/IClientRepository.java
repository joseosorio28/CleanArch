package ports;

import model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientRepository {
    List<Client> findAll();
    Optional<Client> findFirstByIdTypeAndIdNumber(String idType, Long idNumber);
    List<Client>  findByAgeGreaterThan(Integer age);
    void deleteByClientId(Long clientId);
    List<Client> findAllByIdNumberIn(Iterable<Long> idNumbers);
}
