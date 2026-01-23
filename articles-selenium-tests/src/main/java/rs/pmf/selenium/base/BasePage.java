package rs.pmf.selenium.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.pmf.selenium.config.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * Bazna klasa za sve Page Object klase sa wrapovanim Selenium metodama
 */
public class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    
    /**
     * Konstruktor
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.EXPLICIT_WAIT));
    }
    
    // ==================== NAVIGATION ====================
    
    /**
     * Otvara određeni URL
     */
    public void navigateTo(String url) {
        logger.info("Navigacija na URL: {}", url);
        driver.navigate().to(url);
    }
    
    /**
     * Otvara stranicu sa konfigurisanom baznom URL-om
     */
    public void navigateTo(String baseUrl, String path) {
        logger.info("Navigacija na: {}{}", baseUrl, path);
        driver.navigate().to(baseUrl + path);
    }
    
    /**
     * Osvežava stranicu
     */
    public void refreshPage() {
        logger.info("Osvežavanje stranice");
        driver.navigate().refresh();
    }
    
    /**
     * Ide na prethodnu stranicu
     */
    public void goBack() {
        logger.info("Nazad na prethodnu stranicu");
        driver.navigate().back();
    }
    
    // ==================== ELEMENT INTERACTIONS ====================
    
    /**
     * Pronalazi element sa eksplicitnim čekanjem
     */
    protected WebElement findElement(By locator) {
        logger.debug("Pronalaženje elementa: {}", locator);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element nije pronađen: {}", locator);
            throw e;
        }
    }
    
    /**
     * Pronalazi sve elemente sa locator-om
     */
    protected List<WebElement> findElements(By locator) {
        logger.debug("Pronalaženje svih elemenata: {}", locator);
        return driver.findElements(locator);
    }
    
    /**
     * Klikće na element
     */
    public void click(By locator) {
        logger.info("Klik na element: {}", locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    
    /**
     * Klikće direktno na WebElement
     */
    public void click(WebElement element) {
        logger.info("Klik na element");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    
    /**
     * Unosi tekst u element
     */
    public void sendKeys(By locator, String text) {
        logger.info("Unos teksta '{}' u element: {}", text, locator);
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Unosi tekst direktno u WebElement
     */
    public void sendKeys(WebElement element, String text) {
        logger.info("Unos teksta: {}", text);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Proverava da li je element vidljiv
     */
    public boolean isElementVisible(By locator) {
        logger.debug("Provera vidljivosti elementa: {}", locator);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            logger.debug("Element nije vidljiv: {}", locator);
            return false;
        }
    }
    
    /**
     * Proverava da li je element vidljiv
     */
    public boolean isElementVisible(WebElement element) {
        logger.debug("Provera vidljivosti elementa");
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            logger.debug("Element nije vidljiv");
            return false;
        }
    }
    
    /**
     * Proverava da li element postoji
     */
    public boolean isElementPresent(By locator) {
        logger.debug("Provera prisustva elementa: {}", locator);
        return !driver.findElements(locator).isEmpty();
    }
    
    /**
     * Čeka da element bude vidljiv
     */
    public void waitForElementToBeVisible(By locator) {
        logger.info("Čekanje da element bude vidljiv: {}", locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Čeka da element bude klikljiv
     */
    public void waitForElementToBeClickable(By locator) {
        logger.info("Čekanje da element bude klikljiv: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Čeka da element nestane
     */
    public void waitForElementToDisappear(By locator) {
        logger.info("Čekanje da element nestane: {}", locator);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Dobija tekst elementa
     */
    public String getText(By locator) {
        logger.debug("Preuzimanje teksta iz elementa: {}", locator);
        return findElement(locator).getText();
    }
    
    /**
     * Dobija tekst direktno iz WebElement-a
     */
    public String getText(WebElement element) {
        logger.debug("Preuzimanje teksta iz elementa");
        return element.getText();
    }
    
    /**
     * Dobija vrednost atributa
     */
    public String getAttribute(By locator, String attributeName) {
        logger.debug("Preuzimanje atributa '{}' iz elementa: {}", attributeName, locator);
        return findElement(locator).getAttribute(attributeName);
    }
    
    /**
     * Dobija vrednost atributa direktno iz WebElement-a
     */
    public String getAttribute(WebElement element, String attributeName) {
        logger.debug("Preuzimanje atributa: {}", attributeName);
        return element.getAttribute(attributeName);
    }
    
    /**
     * Briše tekst iz elementa
     */
    public void clearField(By locator) {
        logger.info("Brisanje teksta iz elementa: {}", locator);
        findElement(locator).clear();
    }
    
    /**
     * Briše tekst direktno iz WebElement-a
     */
    public void clearField(WebElement element) {
        logger.info("Brisanje teksta iz elementa");
        element.clear();
    }
    
    // ==================== SELECT INTERACTIONS ====================
    
    /**
     * Bira opciju iz dropdown-a po vidljivom tekstu
     */
    public void selectByVisibleText(By locator, String text) {
        logger.info("Izbor opcije '{}' iz dropdown-a: {}", text, locator);
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(text);
    }
    
    /**
     * Bira opciju iz dropdown-a po value atributu
     */
    public void selectByValue(By locator, String value) {
        logger.info("Izbor opcije sa vrednošću '{}' iz dropdown-a: {}", value, locator);
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }
    
    /**
     * Bira opciju iz dropdown-a po index-u
     */
    public void selectByIndex(By locator, int index) {
        logger.info("Izbor opcije sa indeksom {} iz dropdown-a: {}", index, locator);
        Select select = new Select(findElement(locator));
        select.selectByIndex(index);
    }
    
    // ==================== JAVASCRIPT EXECUTION ====================
    
    /**
     * Izvršava JavaScript kod
     */
    public Object executeJavaScript(String script, Object... args) {
        logger.debug("Izvršavanje JavaScript koda");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }
    
    /**
     * Skroluje do elementa
     */
    public void scrollToElement(By locator) {
        logger.info("Skrolovanje do elementa: {}", locator);
        WebElement element = findElement(locator);
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Skroluje do elementa direktno
     */
    public void scrollToElement(WebElement element) {
        logger.info("Skrolovanje do elementa");
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Skroluje do vrha stranice
     */
    public void scrollToTop() {
        logger.info("Skrolovanje na vrh stranice");
        executeJavaScript("window.scrollTo(0, 0);");
    }
    
    /**
     * Skroluje na dnu stranice
     */
    public void scrollToBottom() {
        logger.info("Skrolovanje na dno stranice");
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    // ==================== WINDOW/BROWSER OPERATIONS ====================
    
    /**
     * Dobija naslov stranice
     */
    public String getPageTitle() {
        logger.debug("Preuzimanje naslova stranice");
        return driver.getTitle();
    }
    
    /**
     * Dobija URL stranice
     */
    public String getCurrentUrl() {
        logger.debug("Preuzimanje URL-a stranice");
        return driver.getCurrentUrl();
    }
    
    /**
     * Proverava da li je URL jednak očekivanom
     */
    public boolean isUrlEqual(String expectedUrl) {
        logger.info("Provera URL-a. Očekivani: {}, Stvarni: {}", expectedUrl, getCurrentUrl());
        return getCurrentUrl().equals(expectedUrl);
    }
    
    /**
     * Proverava da li URL sadrži tekst
     */
    public boolean isUrlContains(String urlPart) {
        logger.info("Provera da li URL sadrži: {}", urlPart);
        return getCurrentUrl().contains(urlPart);
    }
    
    /**
     * Dobija čitav HTML stranicu
     */
    public String getPageSource() {
        logger.debug("Preuzimanje HTML koda stranice");
        return driver.getPageSource();
    }
    
    /**
     * Čeka da se stranica učita sa eksplicitnim čekanjem
     */
    public void waitForPageLoad() {
        logger.info("Čekanje da se stranica učita");
        wait.until(webDriver -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            return jsExecutor.executeScript("return document.readyState").equals("complete");
        });
    }
    
    /**
     * Čeka da se element učita sa implicitnim timeout-om
     */
    public void setImplicitWait(int seconds) {
        logger.info("Postavljanje implicitnog timeout-a na {} sekundi", seconds);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
    
    /**
     * Resetuje implicitni timeout na zadanu vrednost
     */
    public void resetImplicitWait() {
        logger.info("Resetovanje implicitnog timeout-a na {} sekundi", Configuration.IMPLICIT_WAIT);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configuration.IMPLICIT_WAIT));
    }
}
