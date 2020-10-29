package ua.i.pl.alex.tests;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import ua.i.pl.alex.BaseDriver;
import ua.i.pl.alex.pages.MainPage;
import ua.i.pl.alex.utils.Properties;

import java.util.List;

public class MainPageTest {
    private static EventFiringWebDriver driver;

    public static void main(String[] args) {
        driver = BaseDriver.getConfiguredDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage(Properties.getBaseUrl());
        String currentCurrency = mainPage.getCurrentCurrency();
        List<String> productsPrices = mainPage.getProductsPrices();
        currencyControl(currentCurrency, productsPrices);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int currsSize = mainPage.getCurrencis().size();
        for (int i = 0; i < currsSize; i++) {
            mainPage.changeCurrency(i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currencyControl(mainPage.getCurrentCurrency(), mainPage.getProductsPrices());
        }
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
