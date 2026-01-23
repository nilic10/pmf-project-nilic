package rs.pmf.selenium.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.pmf.selenium.config.Configuration;
import rs.pmf.selenium.config.DriverFactory;

/**
 * Bazna klasa za sve test klase
 * Omogućava inicijalizaciju i čišćenje WebDriver-a pre i posle svakog testa
 */
public abstract class BaseTest {
    
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    
    /**
     * Setup metoda koja se izvršava pre svakog testa
     */
    @BeforeEach
    public void setUp() {
        logger.info("==================== POČETAK TESTA ====================");
        logger.info("Browser: {}", Configuration.getBrowserType().getValue());
        
        // Kreiranje driver-a
        driver = DriverFactory.createDriver();
        
        // Postavljanje timeout-a
        driver.manage().timeouts()
                .implicitlyWait(java.time.Duration.ofSeconds(Configuration.IMPLICIT_WAIT))
                .pageLoadTimeout(java.time.Duration.ofSeconds(Configuration.PAGE_LOAD_TIMEOUT));
        
        // Maksimizacija prozora
        driver.manage().window().maximize();
        
        logger.info("WebDriver je inicijalizovan");
    }
    
    /**
     * Teardown metoda koja se izvršava posle svakog testa
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            logger.info("Zatvaranje WebDriver-a");
            driver.quit();
        }
        logger.info("==================== KRAJ TESTA ====================\n");
    }
}
