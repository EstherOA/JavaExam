package com.javaexam;

public class Stock extends Product {
    private String ticker;

    Stock(String ID, String exchange, ProductPricingService productPricingService, String ticker) {
        super(ID, exchange);
        super.setValue(productPricingService.price(exchange, ticker));
    }

}
