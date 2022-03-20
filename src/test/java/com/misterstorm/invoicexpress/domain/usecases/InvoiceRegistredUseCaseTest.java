package com.misterstorm.invoicexpress.domain.usecases;

import com.misterstorm.invoicexpress.domain.TestsScenariosAndConstants;
import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.client.ClientRepository;
import com.misterstorm.invoicexpress.domain.service.InvoiceRegisterPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InvoiceRegistredUseCaseTest {

    @Mock
    private InvoiceRegisterPort invoiceRegisterPort;
    @Mock
    private ClientRepository repository;
    @InjectMocks
    private InvoiceRegistredUseCase useCase;

    private TestsScenariosAndConstants constants;

    Client client;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        constants = new TestsScenariosAndConstants();
        client = constants.getClientWithThreeInvoices();
    }

    @Test
    void shouldUpdateInvoice() {
        when(invoiceRegisterPort.registerNewInvoice(any(), any())).thenReturn(Boolean.TRUE);

        useCase.updateInvoiceStatus(client, constants.INVOICE_ID);
        verify(repository, times(1)).persistClient(any());
        assertTrue(client.getAnInvoice(constants.INVOICE_ID).isRegisteredInBahamasGov());
        assertFalse(client.getAnInvoice(constants.SECOUND_INVOICE_ID).isRegisteredInBahamasGov());

    }

    @Test
    void shouldntUpdateInvoice() {
        when(invoiceRegisterPort.registerNewInvoice(any(), any())).thenReturn(Boolean.FALSE);

        useCase.updateInvoiceStatus(client, constants.INVOICE_ID);
        verify(repository, times(0)).persistClient(any());
        assertFalse(client.getAnInvoice(constants.INVOICE_ID).isRegisteredInBahamasGov());

    }

}