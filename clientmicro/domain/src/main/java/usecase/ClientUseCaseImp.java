package usecase;

import model.Client;
import ports.IClientRepository;
import ports.IClientUseCase;
import ports.IImageRepository;

import java.util.List;

public class ClientUseCaseImp implements IClientUseCase {

    private final IClientRepository clientRepository;
    private final IImageRepository imageRepository;

    public ClientUseCaseImp(IClientRepository clientRepository, IImageRepository imageRepository) {
        this.clientRepository = clientRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Client> getClients() {
        return null;
    }

    @Override
    public List<Client> getClientsByAge(Integer age) {
        return null;
    }

    @Override
    public Client searchClient(String idType, Long idNumber) {
        return null;
    }

    @Override
    public void addClient(Client client) {

    }

    @Override
    public void updateClientReview(Client client, String idTypeRequest, Long idNumberRequest) {

    }

    @Override
    public void deleteClient(String idType, Long idNumber) {

    }
}
