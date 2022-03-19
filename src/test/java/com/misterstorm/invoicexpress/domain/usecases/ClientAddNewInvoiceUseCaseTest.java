package com.misterstorm.invoicexpress.domain.usecases;

import com.misterstorm.invoicexpress.domain.model.client.Client;
import com.misterstorm.invoicexpress.domain.model.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static com.misterstorm.invoicexpress.domain.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ClientAddNewInvoiceUseCaseTest {

    @Mock
    private ClientRepository repository;

    @Captor
    ArgumentCaptor<Client> captor;

    @InjectMocks
    private ClientAddNewInvoiceUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void shouldIncludeNewInvoiceInAnExistentUser() {
        when(repository.findByNameAndEmail(any(), any())).thenReturn(this.getClient());

        useCase.addNewInvoice(CLIENT_NAME, CLIENT_EMAIL, SECOUND_INVOICE);

        Mockito.verify(repository).persistClient(captor.capture());
        Client client = captor.getValue();

        assertEquals(EXPECTED_SET_SIZE_2, client.getInvoices().size());
        assertTrue(client.getInvoices().stream().anyMatch(invoice -> invoice.equals(SECOUND_INVOICE)));
        Mockito.verify(repository, times(1)).persistClient(any());
    }

    @Test
    void shouldIncludeInvoiceInAnNewUser() {
        when(repository.findByNameAndEmail(any(), any())).thenReturn(Optional.empty());

        useCase.addNewInvoice(CLIENT_NAME, CLIENT_EMAIL, SECOUND_INVOICE);

        Mockito.verify(repository).persistClient(captor.capture());
        Client client = captor.getValue();

        assertEquals(EXPECTED_SET_SIZE, client.getInvoices().size());
        assertTrue(client.getInvoices().stream().anyMatch(invoice -> invoice.equals(SECOUND_INVOICE)));
        Mockito.verify(repository, times(1)).persistClient(any());
    }

    private Optional<Client> getClient() {
        Client client = new Client(CLIENT_NAME, CLIENT_EMAIL);
        client.addInvoice(INVOICE);
        return Optional.of(client);
    }

}