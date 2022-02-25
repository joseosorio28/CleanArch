package ports;

import model.Client;
import model.Image;

import java.util.List;
import java.util.Optional;

public interface IClientUseCase {
    List<Client> getClients();
    List<Client> getClientsByAge(Integer age);
    Client searchClient(String idType, Long idNumber);
    void addClient(Client client);
    void updateClientReview(Client client, String idTypeRequest, Long idNumberRequest);
    void deleteClient(String idType, Long idNumber);
}
