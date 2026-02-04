package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub");

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "remote-chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Grid URL", e);
                }
                break;
            case "remote-firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Grid URL", e);
                }
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
