package ua.i.pl.alex.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;


public class EventHandler implements WebDriverEventListener {
    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Open "+ s);
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        System.out.println("NavigateBack");
    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        System.out.println("beforeNavigateForward");
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        System.out.println("afterNavigateForward");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {
        System.out.println("beforeNavigateRefresh");
    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        System.out.println("Page refreshed");
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Search element "+by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Element  find successfully");
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Click on "+webElement.getTagName()+" "+webElement.getAttribute("name"));
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Element successfully click");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Fill input "+webElement.getAttribute("id")+" with value "+charSequences.toString());
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println(webElement.getAttribute("id")+" successfully changed");
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {
        System.out.println("Script "+s.toString());
    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {
        
    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        System.out.println("Throwable "+throwable.getMessage());
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }
}
