package com.misterstorm.invoicexpress.domain.usecases;

import com.misterstorm.invoicexpress.domain.TestsScenariosAndConstants;
import com.misterstorm.invoicexpress.domain.event.NewInvoiceEvent;
import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ClientAddNewInvoiceUseCaseTest {

    @Mock
    private ClientRepository repository;

    @Mock
    private NewInvoiceEvent event;

    @Captor
    ArgumentCaptor<Client> captor;

    @InjectMocks
    private ClientAddNewInvoiceUseCase useCase;

    private TestsScenariosAndConstants constants;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        constants = new TestsScenariosAndConstants();

    }

    @Test
    @DisplayName("""
            Given an existent user
            When include a new Invoice
            Should include this invoice""")
    void shouldIncludeNewInvoiceInAnExistentUser() {
        when(repository.findByNameAndEmail(any(), any())).thenReturn(new TestsScenariosAndConstants().getOptionalOfClient());

        useCase.addNewInvoice(constants.CLIENT_NAME, constants.CLIENT_EMAIL,
                constants.SECOUND_INVOICE_ID, constants.SECOUND_FISCAL_ID);

        Mockito.verify(repository).persistClient(captor.capture());
        Client client = captor.getValue();

        assertEquals(constants.EXPECTED_SET_SIZE_2, client.getInvoices().size());
        assertTrue(client.getInvoices().stream().anyMatch(invoice -> invoice.equals(constants.SECOUND_INVOICE)));
        Mockito.verify(repository, times(1)).persistClient(any());
    }

    @Test
    @DisplayName("""
            Given an inexistent user
            When include a new Invoice
            Should create a new user and include this invoice""")
    void shouldIncludeInvoiceInAnNewUser() {
        when(repository.findByNameAndEmail(any(), any())).thenReturn(Optional.empty());

        useCase.addNewInvoice(constants.CLIENT_NAME, constants.CLIENT_EMAIL,
                constants.SECOUND_INVOICE_ID, constants.SECOUND_FISCAL_ID);

        Mockito.verify(repository).persistClient(captor.capture());
        Client client = captor.getValue();

        assertEquals(constants.EXPECTED_SET_SIZE, client.getInvoices().size());
        assertTrue(client.getInvoices().stream().anyMatch(invoice -> invoice.equals(constants.SECOUND_INVOICE)));
        Mockito.verify(repository, times(1)).persistClient(any());
    }

}