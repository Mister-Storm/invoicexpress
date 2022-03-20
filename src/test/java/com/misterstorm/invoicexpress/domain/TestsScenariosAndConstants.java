package com.misterstorm.invoicexpress.domain;

import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;

import java.util.Optional;
import java.util.Set;

public class TestsScenariosAndConstants {

    public TestsScenariosAndConstants() {}

    public final String CLIENT_NAME = "Bob";
    public final String CLIENT_EMAIL = "bob@bob.com";
    public final long INVOICE_ID = 1L;
    public final long FISCAL_ID = 99999L;
    public final long SECOUND_INVOICE_ID = 2L;
    public final long SECOUND_FISCAL_ID = 29999L;
    public final long THIRD_INVOICE_ID = 3L;
    public final long THIRD_FISCAL_ID = 39999L;
    public final Invoice INVOICE = new Invoice(INVOICE_ID, FISCAL_ID);
    public final Invoice SECOUND_INVOICE = new Invoice(SECOUND_INVOICE_ID, SECOUND_FISCAL_ID);
    public final Invoice THIRD_INVOICE = new Invoice(THIRD_INVOICE_ID, THIRD_FISCAL_ID);
    public final int EXPECTED_SET_SIZE = 1;
    public final int EXPECTED_SET_SIZE_2 = 2;
    public final int EXPECTED_SET_SIZE_3 = 3;

    public Optional<Client> getOptionalOfClient() {
        Client client = new Client(CLIENT_NAME, CLIENT_EMAIL);
        client.addInvoice(INVOICE);
        return Optional.of(client);
    }

    public Client getClientWithThreeInvoices() {
        Client client = new Client(CLIENT_NAME, CLIENT_EMAIL);
        client.addAllInvoices(Set.of(INVOICE, SECOUND_INVOICE, THIRD_INVOICE));
        return client;
    }

    public static TestsScenariosAndConstants reset() {
        return new TestsScenariosAndConstants();

    }
}
