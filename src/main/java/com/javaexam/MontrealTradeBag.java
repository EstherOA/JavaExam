package com.javaexam;

import java.util.*;
import java.util.stream.Collectors;

public class MontrealTradeBag implements MontrealTradedProducts{
    Map<Product, Integer> products = new HashMap<>();

    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {
        if(containsProduct(product)) {
            throw new ProductAlreadyRegisteredException("Product "+ product.getID() + " already exists in the records!");
        } else {
            products.put(product, 1);
        }
    }

    @Override
    public void trade(Product product, int quantity) {
        if(containsProduct(product)) {
            products.put(product, quantity);
        } else {
            System.out.println("Product not found. Cannot add to an unknown product");
        }
    }

    public Product removeProduct(Product product) throws ProductNotFoundException{
        if(containsProduct(product)) {
            products.put(product, products.get(product) + 1);
            if(products.get(product) == 1) {
                products.remove(product);
            }
            return product;
        } else {
            throw new ProductNotFoundException("Product not found. Cannot remove an unknown product");
        }
    }

    public Map<Product, Integer> getProductEntries() {
        return products;
    }

    public Object[] getProductList() {
        return products.keySet().toArray();
    }

    public Product getProductById(String ID) {
        return products.keySet().stream().filter(x -> x.getID().equals(ID)).collect(Collectors.toList()).get(0);
    }

    public int getProductQuantity(String ID) {
        return products.get(getProductById(ID));
    }

    public boolean containsProduct(Product product) {
        List<Product> foundProducts = products.keySet().stream().filter(integer -> integer.getID().equals(product.getID())).collect(Collectors.toList());
        return foundProducts.size() > 0;
    }

    @Override
    public int totalTradeQuantityForDay() {
        return products.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        return products.keySet().stream().map(Product::getValue).reduce(0.0, Double::sum);
    }
}
