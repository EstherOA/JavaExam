package com.javaexam;

/**
 * Interface used to audit products that have been registered
 * for tracking. Products can be registered against the
 * API and trades and quantity are tracked. The API is
 * capable of retrieving the total quantity traded for the day
 * and the total value of all the trades.
 */
public interface MontrealTradedProducts {

    /**
     * Adds a new product to the system that
     * the class will track statistics for
     * @param product add a product available for trading
     * @throws ProductAlreadyRegisteredException thrown
     * when a product is registered twice
     */
    void addNewProduct(Product product) throws ProductAlreadyRegisteredException;

    /**
     * Books a quantity against the product traded. If the product
     * has not been registered, no quantity is recorded as
     * it is not a product we are required to track.
     * @param product the product traded
     * @param quantity the quantity traded
     */
    void trade(Product product, int quantity);

    /**
     * Calculates the total quantity of all the registered
     * traded products so far today
     * @return the total quantity traded
     */
    int totalTradeQuantityForDay();

    /**
     * Calculates the total value of all the registered traded products
     * so far today. This is done by multiplying the value by the quantity
     * traded.
     *
     * @return the total value of today's traded products that are
     *         registered in the system
     */
    double totalValueOfDaysTradedProducts();
}
