package com.misterstorm.invoicexpress.domain.model.client;

import java.util.Optional;

public interface ClientRepository {

    Client persistClient(Client client);

    Optional<Client> findByNameAndEmail(String name, String email);
}
