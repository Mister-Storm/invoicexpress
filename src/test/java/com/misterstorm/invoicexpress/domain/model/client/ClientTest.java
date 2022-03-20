package com.misterstorm.invoicexpress.domain.model.client;

import com.misterstorm.invoicexpress.domain.TestsScenariosAndConstants;
import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;
import com.misterstorm.invoicexpress.domain.model.invoice.InvoiceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ClientTest {

    TestsScenariosAndConstants constants;
    Client client;

    @BeforeEach
    public void setup() {
        constants = new TestsScenariosAndConstants();
        this.client = new Client(constants.CLIENT_NAME, constants.CLIENT_EMAIL);
    }
    @Test
    @DisplayName("""
            Given an existent client
            When add a new invoice
            Then should include the new invoice""")
    void shouldAddNewInvoice() {
        
        this.client.addInvoice(constants.INVOICE);
        this.client.addInvoice(constants.SECOUND_INVOICE);

        assertEquals(constants.EXPECTED_SET_SIZE_2, client.getInvoices().size());
        assertTrue( client.getInvoices().stream().allMatch(invoice -> invoice.equals(constants.INVOICE)
                || invoice.equals(constants.SECOUND_INVOICE)));

    }

    @Test
    @DisplayName("""
            Given an existent client
            When add a invoice existent
            Then shouldn't include the invoice""")
    void shouldntAddExistentInvoice() {

        client.addInvoice(constants.INVOICE);
        client.addInvoice(constants.INVOICE);

        assertEquals(constants.EXPECTED_SET_SIZE, client.getInvoices().size());
        assertArrayEquals(Set.of(constants.INVOICE).toArray(), client.getInvoices().toArray());
    }

    @Test
    @DisplayName("""
            Given an existent client
            When add a new invoices
            Then should include the new invoices""")
    void shouldIncludeAllNewInvoices() {

        client.addInvoice(constants.INVOICE);
        client.addAllInvoices(Set.of(constants.SECOUND_INVOICE, constants.THIRD_INVOICE));

        assertEquals(constants.EXPECTED_SET_SIZE_3, client.getInvoices().size());
        assertTrue( client.getInvoices().stream().allMatch(invoice -> invoice.equals(constants.INVOICE)
                || invoice.equals(constants.SECOUND_INVOICE)
                || invoice.equals(constants.THIRD_INVOICE)));
    }

    @Test
    @DisplayName("""
            Given an existent client
            When getAnInvoice()
            Then should return the Invoice""")
    void ShouldReturnTheInvoice() {

        client.addInvoice(constants.INVOICE);
        client.addAllInvoices(Set.of(constants.SECOUND_INVOICE, constants.THIRD_INVOICE));

        Invoice invoice = client.getAnInvoice(constants.SECOUND_INVOICE_ID);

        assertEquals(constants.SECOUND_INVOICE, invoice);

    }

    @Test
    @DisplayName("""
            Given an existent client
            When getAnInvoice() and the invoice not present
            Then should throw exception""")
    void ShouldThrowExceptionWhenInexistentInvoice() {

        client.addInvoice(constants.INVOICE);
        client.addInvoice(constants.THIRD_INVOICE);

        assertThrows(InvoiceNotFoundException.class, () -> client.getAnInvoice(constants.SECOUND_INVOICE_ID));

    }

    @Test
    @DisplayName("""
            Given an existent client
            When a invoice has registred
            Then should update Invoice status""")
    void ShouldRegisterAnInvoice() {

        client.addInvoice(constants.INVOICE);
        client.addAllInvoices(Set.of(constants.SECOUND_INVOICE, constants.THIRD_INVOICE));

        client.updateInvoice(constants.SECOUND_INVOICE_ID);
        Invoice invoice = client.getAnInvoice(constants.SECOUND_INVOICE_ID);

        assertEquals(constants.SECOUND_INVOICE, invoice);
        assertTrue(invoice.isRegisteredInBahamasGov());
        assertFalse(client.getAnInvoice(constants.INVOICE_ID).isRegisteredInBahamasGov());

    }

}