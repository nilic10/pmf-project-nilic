package com.example.selenium.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Factory class for creating WebDriver instances.
 * Supports Chrome, Firefox, and remote execution via Selenium Grid.
 */
public class DriverFactory {

    /**
     * Creates and returns a WebDriver instance for the specified browser.
     * Supports "chrome", "firefox", "remote-chrome", and "remote-firefox".
     * Remote instances use the URL provided by the 'gridUrl' system property (default: http://localhost:4444/wd/hub).
     * 
     * @param browser The browser type to initialize.
     * @return Initialized WebDriver instance, maximized.
     * @throws IllegalArgumentException If the browser type is not supported.
     * @throws RuntimeException If there is a MalformedURLException for the Grid URL.
     */
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
