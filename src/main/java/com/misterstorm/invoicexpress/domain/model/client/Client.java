package com.misterstorm.invoicexpress.domain.model.client;

import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;
import com.misterstorm.invoicexpress.domain.model.invoice.InvoiceNotFoundException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Client {

    private final String name;
    private final String email;
    private Set<Invoice> invoices;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        this.invoices = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<Invoice> getInvoices() {
        return Collections.unmodifiableSet(invoices);
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }
    public void addAllInvoices(Set<Invoice> invoices) {
        this.invoices.addAll(invoices);
    }
    public Invoice getAnInvoice(Long invoiceId) {
        return this.invoices.stream()
                .filter(invoice -> invoice.getId().equals(invoiceId))
                .findFirst().orElseThrow(() -> new InvoiceNotFoundException(invoiceId));
    }
    public void updateInvoice(Long invoiceId) {
        this.invoices.stream()
                .filter(invoice -> invoice.getId().equals(invoiceId))
                .forEach(Invoice::registrationPerformed);

    }

}
