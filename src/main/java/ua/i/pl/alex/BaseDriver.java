package ua.i.pl.alex;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ua.i.pl.alex.utils.EventHandler;
import ua.i.pl.alex.utils.Properties;

import java.util.concurrent.TimeUnit;


public abstract class BaseDriver {
    private final static String DRIVER_PATH = System.getProperty("user.dir");
    private final static String SEPARATOR = System.getProperty("file.separator");
    private static WebDriver chromeDriver;
    private static WebDriver ffDriver;
    private static WebDriver iEDriver;

    private static WebDriver getChromeDriver() {
        String key = System.getProperty("webdriver.chrome.driver");
        if (key == null) {
            System.setProperty("webdriver.chrome.driver",
                    DRIVER_PATH + SEPARATOR + "target" + SEPARATOR + "classes" + SEPARATOR + "chromedriver.exe");
            chromeDriver = new ChromeDriver();
        }
        return chromeDriver;
    }

    private static WebDriver getFFDriver() {
        String key = System.getProperty("webdriver.gecko.driver");
        if (key == null) {
            System.setProperty("webdriver.gecko.driver",
                    DRIVER_PATH + SEPARATOR + "target" + SEPARATOR + "classes" + SEPARATOR + "geckodriver.exe");
            ffDriver=new FirefoxDriver();
        }
        return ffDriver;
    }

    private static WebDriver getIEDriver() {
        String key = System.getProperty("webdriver.ie.driver");
        if (key == null) {
            System.setProperty("webdriver.ie.driver",
                    DRIVER_PATH + SEPARATOR + "target" + SEPARATOR + "classes" + SEPARATOR + "IEDriverServer.exe");
            iEDriver=new InternetExplorerDriver();
        }
        return iEDriver;
    }

    private static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        if (browser == null) {
            return getChromeDriver();
        } else {
            switch (browser) {
                case "chrome":
                    return getChromeDriver();
                case "gecko":
                    return getFFDriver();
                case "ie":
                    return getIEDriver();
                default:
                    throw new RuntimeException("Wrong browser name");
            }

        }
    }

    public static EventFiringWebDriver getConfiguredDriver() {
        WebDriver baseDriver = getDriver();
        baseDriver.manage().window().maximize();
        baseDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EventFiringWebDriver driver = new EventFiringWebDriver(baseDriver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.register(new EventHandler());
        return driver;
    }

    public static void quitDriver(EventFiringWebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
