package com.javaexam;

public class Future extends Product{
    private String contractCode;
    private int contractMonth;
    private int contractYear;

    public Future(String ID, String exchange, ProductPricingService productPricingService, String contractCode, int contractMonth, int contractYear) {
        super(ID, exchange);
        this.contractCode = contractCode;
        this.contractMonth = contractMonth;
        this.contractYear = contractYear;
        super.setValue(productPricingService.price(exchange, contractCode, contractMonth, contractYear));
    }

}
