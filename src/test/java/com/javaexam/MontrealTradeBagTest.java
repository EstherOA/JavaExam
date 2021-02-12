package com.javaexam;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MontrealTradeBagTest {
    private List<Product> sampleProducts = new ArrayList<>(4);
    private final String sampleTicker = "TICKER";
    private final String sampleContract = "CONTRACT";
    private final String sampleExchange = "EXCHANGE";
    private final int sampleMonth = 2;
    private final int sampleYear = 2020;
    MontrealTradeBag todayBag;

    @Before
    public void setUp() throws Exception {
        ProductPricingService mockPricingService = Mockito.mock(ProductPricingService.class);
        Mockito.when(mockPricingService.price(Mockito.anyString(), Mockito.anyString())).thenReturn(56.9);
        Mockito.when(mockPricingService.price(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(150.0);
        IntStream.range(0,4).forEach(i -> {
            Product newProduct = new Stock(""+i ,sampleExchange, mockPricingService, sampleTicker);
            if(i%2 > 0) {
                newProduct = new Future(""+i, sampleExchange, mockPricingService, sampleContract, sampleMonth, sampleYear);
            }
            sampleProducts.add(newProduct);
        });
        this.todayBag = new MontrealTradeBag();

    }

    @Test
    public void addNewProduct() {
        //test adding a new stock
        try {
            List<Product> expectedResults = sampleProducts.subList(1,3);
            todayBag.addNewProduct(sampleProducts.get(2));
            todayBag.addNewProduct(sampleProducts.get(1));
            assertEquals(todayBag.getProductList().length, expectedResults.toArray().length);
            assertEquals(todayBag.getProductById("2"), sampleProducts.get(2));
            assertEquals(todayBag.getProductById("1"), sampleProducts.get(1));
        } catch (ProductAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ProductAlreadyRegisteredException.class)
    public void testAddingExistingProduct() throws ProductAlreadyRegisteredException{
            todayBag.addNewProduct(sampleProducts.get(0));
            todayBag.addNewProduct(sampleProducts.get(0));
    }

    @Test
    public void trade() {
        //test a trade
        try {
            todayBag.addNewProduct(sampleProducts.get(3));
            todayBag.addNewProduct(sampleProducts.get(0));
            todayBag.trade(sampleProducts.get(3), 8);
            todayBag.trade(sampleProducts.get(0), 53);
            assertEquals(todayBag.getProductQuantity("3"), 8);
            assertEquals(todayBag.getProductQuantity("0"), 53);
        } catch (ProductAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void totalTradeQuantityAndValueForDay() {
        try {
            todayBag.addNewProduct(sampleProducts.get(0));
            todayBag.addNewProduct(sampleProducts.get(1));
            todayBag.addNewProduct(sampleProducts.get(2));
            todayBag.addNewProduct(sampleProducts.get(3));
            todayBag.trade(sampleProducts.get(3), 8);
            todayBag.trade(sampleProducts.get(0), 53);
            assertEquals(todayBag.totalTradeQuantityForDay(), 63);
            assertEquals(todayBag.totalValueOfDaysTradedProducts(), 413.8, 0.0001);
        } catch (ProductAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

}