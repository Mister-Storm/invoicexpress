package com.misterstorm.invoicexpress.domain.model.invoice;

import java.util.Objects;

public class Invoice {

    private final Long id;
    private final Long fiscalId;
    private boolean registeredInBahamasGov;

    public Invoice(Long id, Long fiscalId) {
        this.id = id;
        this.fiscalId = fiscalId;
        this.registeredInBahamasGov = false;
    }

    public Long getId() {
        return id;
    }

    public Long getFiscalId() {
        return fiscalId;
    }

    public boolean isRegisteredInBahamasGov() {
        return registeredInBahamasGov;
    }

    public void registrationPerformed() {
        this.registeredInBahamasGov = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(fiscalId, invoice.fiscalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fiscalId);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", fiscalId=" + fiscalId +
                '}';
    }
}
