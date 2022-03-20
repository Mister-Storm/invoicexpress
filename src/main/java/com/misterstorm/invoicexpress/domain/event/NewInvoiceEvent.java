package com.misterstorm.invoicexpress.domain.event;

import com.misterstorm.invoicexpress.domain.model.client.Client;

public interface NewInvoiceEvent {

    void onNewInvoice(Client client, Long invoiceId);

}
