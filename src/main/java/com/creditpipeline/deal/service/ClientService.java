package com.creditpipeline.deal.service;

import com.creditpipeline.deal.entity.Client;
import com.creditpipeline.deal.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client){
        clientRepository.save(client);
    }

}
