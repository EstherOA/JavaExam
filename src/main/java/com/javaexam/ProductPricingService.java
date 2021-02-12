package com.javaexam;

public interface ProductPricingService {
    double price(String exchange, String ticker);
    double price(String exchange, String contractCode, int month, int year);
}
