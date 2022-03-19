package com.misterstorm.invoicexpress.domain.model.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.misterstorm.invoicexpress.domain.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;


class ClientTest {

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