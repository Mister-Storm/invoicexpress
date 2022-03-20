package com.misterstorm.invoicexpress.domain.service;

import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;

public interface InvoiceRegisterPort {

    boolean registerNewInvoice(Client client, Invoice invoice);

}
