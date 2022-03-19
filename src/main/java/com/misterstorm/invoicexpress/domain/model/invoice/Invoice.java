package com.misterstorm.invoicexpress.domain.model.invoice;

import java.util.Objects;

public class Invoice {

    private final Long id;
    private final Long fiscalId;

    public Invoice(Long id, Long fiscalId) {
        this.id = id;
        this.fiscalId = fiscalId;
    }

    public Long getId() {
        return id;
    }

    public Long getFiscalId() {
        return fiscalId;
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
