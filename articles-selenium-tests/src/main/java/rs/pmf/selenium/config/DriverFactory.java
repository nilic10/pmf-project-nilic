package rs.pmf.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Factory klasa za kreiranje WebDriver instanci
 */
public class DriverFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    
    /**
     * Kreira WebDriver na osnovu konfiguracije
     * @return WebDriver instanca
     */
    public static WebDriver createDriver() {
        BrowserType browserType = Configuration.getBrowserType();
        logger.info("Kreiranje WebDriver za browser: {}", browserType.getValue());
        
        return switch (browserType) {
            case CHROME -> createChromeDriver();
            case FIREFOX -> createFirefoxDriver();
            case REMOTE_CHROME -> createRemoteChromeDriver();
            case REMOTE_FIREFOX -> createRemoteFirefoxDriver();
            default -> createChromeDriver();
        };
    }
    
    /**
     * Kreira lokalni Chrome driver
     */
    private static WebDriver createChromeDriver() {
        logger.info("Pokretanje Chrome drivera");
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        // Opciono - pode se dodati headless mode
        // options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        return new ChromeDriver(options);
    }
    
    /**
     * Kreira lokalni Firefox driver
     */
    private static WebDriver createFirefoxDriver() {
        logger.info("Pokretanje Firefox drivera");
        WebDriverManager.firefoxdriver().setup();
        
        FirefoxOptions options = new FirefoxOptions();
        // Opciono - pode se dodati headless mode
        // options.addArguments("--headless");
        
        return new FirefoxDriver(options);
    }
    
    /**
     * Kreira remote Chrome driver na Selenium Grid-u
     */
    private static WebDriver createRemoteChromeDriver() {
        logger.info("Pokretanje Remote Chrome drivera na Grid-u");
        try {
            ChromeOptions options = new ChromeOptions();
            URL gridUrl = new URL(Configuration.getGridUrl());
            return new RemoteWebDriver(gridUrl, options);
        } catch (MalformedURLException e) {
            logger.error("Greška pri kreiranju Remote Chrome drivera", e);
            throw new RuntimeException("Greška pri kreiranju Remote Chrome drivera: " + e.getMessage());
        }
    }
    
    /**
     * Kreira remote Firefox driver na Selenium Grid-u
     */
    private static WebDriver createRemoteFirefoxDriver() {
        logger.info("Pokretanje Remote Firefox drivera na Grid-u");
        try {
            FirefoxOptions options = new FirefoxOptions();
            URL gridUrl = new URL(Configuration.getGridUrl());
            return new RemoteWebDriver(gridUrl, options);
        } catch (MalformedURLException e) {
            logger.error("Greška pri kreiranju Remote Firefox drivera", e);
            throw new RuntimeException("Greška pri kreiranju Remote Firefox drivera: " + e.getMessage());
        }
    }
}
