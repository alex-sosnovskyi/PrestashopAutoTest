package ua.i.pl.alex.utils;

import org.openqa.selenium.remote.BrowserType;


 public class Properties {
    private static final String DEFAULT_BASE_URL = "http://prestashop-automation.qatestlab.com.ua/ru/";
    private static final String DEFAULT_BROWSER = BrowserType.CHROME;


    public static String getBaseUrl() {
        return System.getProperty(EnvVars.BASE_URL.toString(), DEFAULT_BASE_URL);
    }


    public static String getBrowser() {
        return System.getProperty(EnvVars.BROWSER.toString(), DEFAULT_BROWSER);
    }

}

enum EnvVars {
    BASE_URL("env.url"),
    BASE_ADMIN_URL("env.admin.url"),
    BROWSER("browser");

    private String value;
    EnvVars(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
