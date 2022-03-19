package com.misterstorm.invoicexpress.domain;

import com.misterstorm.invoicexpress.domain.model.invoice.Invoice;

public class TestConstants {

    private TestConstants() {}

    public static final String CLIENT_NAME = "Bob";
    public static final String CLIENT_EMAIL = "bob@bob.com";
    public static final long INVOICE_ID = 1L;
    public static final long FISCAL_ID = 99999L;
    public static final long SECOUND_INVOICE_ID = 2L;
    public static final long SECOUND_FISCAL_ID = 29999L;
    public static final long THIRD_INVOICE_ID = 3L;
    public static final long THIRD_FISCAL_ID = 39999L;
    public static final Invoice INVOICE = new Invoice(INVOICE_ID, FISCAL_ID);
    public static final Invoice SECOUND_INVOICE = new Invoice(SECOUND_INVOICE_ID, SECOUND_FISCAL_ID);
    public static final Invoice THIRD_INVOICE = new Invoice(THIRD_INVOICE_ID, THIRD_FISCAL_ID);
    public static final int EXPECTED_SET_SIZE = 1;
    public static final int EXPECTED_SET_SIZE_2 = 2;
    public static final int EXPECTED_SET_SIZE_3 = 3;
}
