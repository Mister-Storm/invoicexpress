package com.misterstorm.invoicexpress.domain.usecases;

import com.misterstorm.invoicexpress.domain.event.NewInvoiceEvent;
import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.client.ClientRepository;
import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;

import java.util.Optional;

public class ClientAddNewInvoiceUseCase {

    private final ClientRepository repository;
    private final NewInvoiceEvent event;

    public ClientAddNewInvoiceUseCase(ClientRepository repository, NewInvoiceEvent event) {
        this.repository = repository;
        this.event = event;
    }

    protected Client addNewInvoice(String name, String email, Long invoiceId, Long fiscalId) {
        Optional<Client> optClient = repository.findByNameAndEmail(name, email);
        Client client = optClient.orElseGet(() -> new Client(name, email));
        Invoice invoice = new Invoice(invoiceId, fiscalId);
        client.addInvoice(invoice);
        repository.persistClient(client);
        event.onNewInvoice(client, invoice.getId());
        return client;
    }


}
