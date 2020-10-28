package ua.i.pl.alex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.i.pl.alex.models.PruductData;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class SearchPage {
    private EventFiringWebDriver driver;
    private By products = By.cssSelector(".product-miniature");
    private By productsTopSelector = By.cssSelector("#js-product-list-top>div>p");
    private By currCurrencySelector = By.cssSelector(".currency-selector>span.expand-more");
    private By sortMenuSelector = By.xpath("//*[@id=\"js-product-list-top\"]/div[2]/div/div/div/a[5]");
    private By sortMenuLinkSelector = By.cssSelector(".select-title");
    private By logoSelector = By.cssSelector("#_desktop_logo>a");

    public SearchPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public int getProductsTotal() {
        WebElement element = driver.findElement(productsTopSelector);
        String text = element.getText();
        String substring = text.substring(text.indexOf(" ") + 1, text.indexOf("."));
        return Integer.parseInt(substring);
    }

    public int getTotalCount() {
        List<WebElement> elements = driver.findElements(products);
        return elements.size();
    }

    public List<String> getProductsPrices() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(logoSelector));
        List<WebElement> elements = driver.findElements(products);
        List<String> currancys = new ArrayList<>();
        for (WebElement element : elements) {
            WebElement element1 = element.findElement(By.cssSelector(".price"));
            currancys.add(element1.getText());
        }
        return currancys;
    }

    public String getCurrentCurrency() {
        WebElement element = driver.findElement(currCurrencySelector);
        String s = element.getText().toLowerCase();
        s = s.substring(s.length() - 1);
        return s;
    }

    public void sort() {
        WebElement sortMenu = driver.findElement(sortMenuLinkSelector);
        sortMenu.click();
        WebElement element = driver.findElement(sortMenuSelector);
        element.click();
    }

    public List<PruductData> getProdData() {
        List<PruductData> datas = new ArrayList<>();
        List<WebElement> elements = driver.findElements(products);
        for (WebElement el : elements) {
            List<WebElement> elChilds = el.findElements(By.tagName("span"));
            PruductData pruductData = new PruductData();
            for (WebElement element: elChilds) {
                String aClass = element.getAttribute("class");
                String text = element.getText();
                switch (aClass){
                    case "price":{
                        pruductData.setPrice(text);
                        break;
                    }
                    case "regular-price":{
                        pruductData.setRegPrice(text);
                        break;
                    }
                    case "discount-percentage":{
                        pruductData.setDiscount(text);
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
            datas.add(pruductData);
        }
        return datas;
    }
}
