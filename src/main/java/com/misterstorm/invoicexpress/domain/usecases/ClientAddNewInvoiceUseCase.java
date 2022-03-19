package com.misterstorm.invoicexpress.domain.usecases;

import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.client.ClientRepository;
import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;

import java.util.Optional;

public class ClientAddNewInvoiceUseCase {

    private final ClientRepository repository;

    public ClientAddNewInvoiceUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    protected Client addNewInvoice(String name, String email, Invoice invoice) {
        Optional<Client> optClient = repository.findByNameAndEmail(name, email);
        Client client = optClient.orElseGet(() -> new Client(name, email));
        client.addInvoice(invoice);
        return repository.persistClient(client);
    }


}
