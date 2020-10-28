package ua.i.pl.alex.tests;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import ua.i.pl.alex.BaseDriver;
import ua.i.pl.alex.models.PruductData;
import ua.i.pl.alex.pages.MainPage;
import ua.i.pl.alex.pages.SearchPage;
import ua.i.pl.alex.utils.Properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPageTest {
    private static String searchText = "dress";
    private static EventFiringWebDriver driver;

    public static void main(String[] args) {
        driver = BaseDriver.getConfiguredDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage(Properties.getBaseUrl());
        mainPage.changeCurrency(2);
        mainPage.fillSearchInput(searchText);
        mainPage.clickSearchButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchPage searchPage = new SearchPage(driver);
        countTest(searchPage.getProductsTotal(), searchPage.getTotalCount());
        List<String> productPrices = new ArrayList<>();
        productPrices.addAll(searchPage.getProductsPrices());
        currencyControl(searchPage.getCurrentCurrency(), productPrices);
        searchPage.sort();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPrices.clear();
        productPrices.addAll(searchPage.getProductsPrices());
        sortControl(productPrices);
        List<PruductData> prodData = searchPage.getProdData();
        testDiscount(prodData);
    }

    private static void testDiscount(List<PruductData> prodData) {
        PruductData pruductData = prodData.stream().filter(product -> product.getRegPrice() > 0.0).filter(product -> {
            float discount = product.getDiscount();
            float regPrice = product.getRegPrice();
            float price = product.getPrice();
            float sumDisc = regPrice * Math.abs(discount)/100;

            if (Math.abs((sumDisc+price)-regPrice) > 0.01) {
                return true;
            } else {
                return false;
            }
        }).findAny().orElse(null);
        if(pruductData==null){
            System.out.println("testDiscount succesful");
        }else{
            System.out.println("testDiscount failed");
        }
    }

    private static void sortControl(List<String> productPrices) {
        List<Float> ddd = new ArrayList<>();
        for (String s : productPrices) {
            String substring = s.substring(0, s.indexOf(" "));
            String replace = substring.replace(',', '.');
            Float f = Float.valueOf(replace);
            ddd.add(f);
        }
        Float min = Collections.min(ddd);
        int size = ddd.size();
        Float aFloat = ddd.get(size - 1);
        boolean b = min - aFloat == 0;
        System.out.println("sortControl is " + b);
    }

    private static void countTest(int productsTotal, int totalCount) {
        boolean result = productsTotal - totalCount == 0;
        System.out.println("Count Test is " + result);
    }

    private static void currencyControl(String currentCurrency, List<String> productsPrices) {
        String s1 = productsPrices.stream().map(s -> s.substring(s.length() - 1))
                .filter(s -> !s.equals(currentCurrency))
                .findAny().orElse("");
        if (s1.isEmpty()) {
            System.out.println("Populare Category Currancy all asserts currancy=" + currentCurrency);
        } else {
            System.out.println("Populare Category Currancy not asserts currancy=" + currentCurrency);
        }
    }
}
