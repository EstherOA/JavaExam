package com.javaexam;

abstract class Product {
    private String ID;
    private double value;
    private String exchange;

    Product(String ID, String exchange) {
        this.ID = ID;
        this.exchange = exchange;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getID() {
        return ID;
    }
}
