package com.misterstorm.invoicexpress.domain.model.invoice;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(Long id) {
        super(String.format("Invoice not found %d", id));
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
