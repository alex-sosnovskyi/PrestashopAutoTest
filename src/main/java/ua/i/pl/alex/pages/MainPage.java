package ua.i.pl.alex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class MainPage {
    private EventFiringWebDriver driver;
    private By productPriceSelector = By.cssSelector(".price");
    private By currCurrencySelector = By.cssSelector(".currency-selector>span.expand-more");
    private By currMenuSelector = By.cssSelector(".currency-selector>a");
    private  By currancySelector = By.cssSelector(".currency-selector>ul.dropdown-menu>li>a");
    private  WebElement searchForm;
    private By searchFormSelector = By.cssSelector("#search_widget>form");
    private By searchInputSelector = By.name("s");
    private By submitSelector = By.tagName("button");
    private By logoSelector = By.cssSelector("#_desktop_logo>a");

    public MainPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void openPage(String url) {
        if (url == null) {
            throw new RuntimeException("Page is null!!!");
        }
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            throw new RuntimeException("url is incorrect!!!");
        }

    }

    public List<String> getProductsPrices() {
        List<WebElement> elements;
        List<String> productPrices = new ArrayList<>();
        elements = driver.findElements(productPriceSelector);
        for (WebElement element : elements) {
            productPrices.add(element.getText().toLowerCase());
        }
        return new ArrayList<>(productPrices);
    }

    public String getCurrentCurrency() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(logoSelector));
        WebElement element = driver.findElement(currCurrencySelector);
        String s = element.getText().toLowerCase();
        s = s.substring(s.length() - 1);
        return s;
    }

    public List<WebElement> getCurrencis(){
        List<WebElement> elements;
        elements = driver.findElements(currancySelector);
        return new ArrayList<>(elements);
    }
    public void changeCurrency(int index) {
        driver.findElement(currMenuSelector).click();
        WebElement element=getCurrencis().get(index);
        element.click();
    }

    public void fillSearchInput(String text){
        searchForm = driver.findElement(searchFormSelector);
        WebElement searchInput = searchForm.findElement(searchInputSelector);
        if (searchInput==null){
            throw new RuntimeException("login form is not available!!!");
        }
        searchInput.sendKeys(text);
    }

    public void clickSearchButton(){
        if (searchForm==null){
            throw new RuntimeException("search form is not available!!!");
        }
        WebElement submit = searchForm.findElement(submitSelector);
        submit.click();
    }
}
