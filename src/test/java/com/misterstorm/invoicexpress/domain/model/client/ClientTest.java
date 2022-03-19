package com.misterstorm.invoicexpress.domain.model.client;

import static org.junit.jupiter.api.Assertions.*;

import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;


class ClientTest {

    private static final String CLIENT_NAME = "Bob";
    private static final String CLIENT_EMAIL = "bob@bob.com";
    private static final long INVOICE_ID = 1L;
    private static final long FISCAL_ID = 99999L;
    private static final long SECOUND_INVOICE_ID = 2L;
    private static final long SECOUND_FISCAL_ID = 29999L;
    private static final long THIRD_INVOICE_ID = 3L;
    private static final long THIRD_FISCAL_ID = 39999L;
    private static final Invoice INVOICE = new Invoice(INVOICE_ID, FISCAL_ID);
    private static final Invoice SECOUND_INVOICE = new Invoice(SECOUND_INVOICE_ID, SECOUND_FISCAL_ID);
    private static final Invoice THIRD_INVOICE = new Invoice(THIRD_INVOICE_ID, THIRD_FISCAL_ID);
    private static final int EXPECTED_SET_SIZE = 1;
    private static final int EXPECTED_SET_SIZE_2 = 2;
    private static final int EXPECTED_SET_SIZE_3 = 3;

    Client client;

    @BeforeEach
    public void setup() {
        this.client = new Client(CLIENT_NAME, CLIENT_EMAIL);
    }
    @Test
    @DisplayName("""
            Given an existent client
            When add a new invoice
            Then should include the new invoice""")
    void shouldAddNewInvoice() {
        
        this.client.addInvoice(INVOICE);
        this.client.addInvoice(SECOUND_INVOICE);

        assertEquals(EXPECTED_SET_SIZE_2, client.getInvoices().size());
        assertTrue( client.getInvoices().stream().allMatch(invoice -> invoice.equals(INVOICE) || invoice.equals(SECOUND_INVOICE)));

    }

    @Test
    @DisplayName("""
            Given an existent client
            When add a invoice existent
            Then shouldn't include the invoice""")
    void shouldntAddExistentInvoice() {

        client.addInvoice(INVOICE);
        client.addInvoice(INVOICE);

        assertEquals(EXPECTED_SET_SIZE, client.getInvoices().size());
        assertArrayEquals(Set.of(INVOICE).toArray(), client.getInvoices().toArray());
    }

    @Test
    @DisplayName("""
            Given an existent client
            When add a new invoices
            Then should include the new invoices""")
    void shouldIncludeAllNewInvoices() {

        client.addInvoice(INVOICE);
        client.addAllInvoices(Set.of(SECOUND_INVOICE,THIRD_INVOICE));

        assertEquals(EXPECTED_SET_SIZE_3, client.getInvoices().size());
        assertTrue( client.getInvoices().stream().allMatch(invoice -> invoice.equals(INVOICE)
                || invoice.equals(SECOUND_INVOICE)
                || invoice.equals(THIRD_INVOICE)));
    }

}