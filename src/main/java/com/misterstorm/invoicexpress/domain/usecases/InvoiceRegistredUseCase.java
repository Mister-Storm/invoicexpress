package com.misterstorm.invoicexpress.domain.usecases;

import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.client.ClientRepository;
import com.misterstorm.invoicexpress.domain.service.InvoiceRegisterPort;


public class InvoiceRegistredUseCase {

    private final InvoiceRegisterPort invoiceRegisterPort;
    private final ClientRepository repository;

    public InvoiceRegistredUseCase(InvoiceRegisterPort invoiceRegisterPort, ClientRepository repository) {
        this.invoiceRegisterPort = invoiceRegisterPort;
        this.repository = repository;
    }

    protected void updateInvoiceStatus(Client client, Long invoiceId) {

        if(invoiceRegisterPort.registerNewInvoice(client, client.getAnInvoice(invoiceId))) {
            client.updateInvoice(invoiceId);
            repository.persistClient(client);
        }
    }

    protected void updateInvoiceStatus(Client client) {
        client.getInvoices().stream().filter(invoice -> !invoice.isRegisteredInBahamasGov())
                .forEach(invoice -> updateInvoiceStatus(client, invoice.getId()));
    }

}
