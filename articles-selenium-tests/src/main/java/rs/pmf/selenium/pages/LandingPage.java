package rs.pmf.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rs.pmf.selenium.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Object klasa za početnu stranicu / Landing Page aplikacije
 */
public class LandingPage extends BasePage {

    // ==================== LOCATORS ====================

    // Naslov i opis
    private static final By PAGE_TITLE = By.xpath("//h1 | //*[contains(text(), 'Welcome')]");
    private static final By PAGE_DESCRIPTION = By.xpath("//*[contains(text(), 'Explore and create')]");

    // Let's Start dugme
    private static final By LETS_START_BUTTON = By.xpath("//button[contains(text(), \"Let's start\")]");

    // Footer informacije
    private static final By FOOTER_VERSION = By.xpath("//*[contains(text(), 'Version:')]");
    private static final By FOOTER_COPYRIGHT = By.xpath("//*[contains(text(), 'Copyright')]");
    private static final By FOOTER_LINK = By.xpath("//a[contains(@href, 'jaktestowac')]");

    // Tema/language dugmići (ako postoje)
    private static final By THEME_BUTTONS = By.xpath("//button[contains(@class, 'theme')] | //button[contains(@title, 'theme')]");

    /**
     * Konstruktor
     */
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Proverava da li je korisnik na Landing Page stranici
     */
    public boolean isOnLandingPage() {
        logger.debug("Provera da li je korisnik na Landing Page stranici");
        boolean hasTitle = isElementPresent(PAGE_TITLE);
        boolean hasButton = isElementPresent(LETS_START_BUTTON);
        boolean isOn = hasTitle && hasButton;
        logger.info("Korisnik je {}na Landing Page stranici", isOn ? "" : "ni ");
        return isOn;
    }

    /**
     * Verifikacija: korisnik je na Landing Page stranici (asertacija)
     */
    public LandingPage verifyOnLandingPage() {
        logger.info("Verifikacija da je korisnik na Landing Page");
        assertTrue(isOnLandingPage(), "Očekivano je da je korisnik na Landing Page stranici, ali nije.");
        return this;
    }

    // ==================== SADRŽAJ ====================

    /**
     * Dobija naslov stranice
     */
    public String getPageTitle() {
        logger.debug("Preuzimanje naslova stranice");
        return getText(PAGE_TITLE);
    }

    /**
     * Dobija opis stranice
     */
    public String getPageDescription() {
        logger.debug("Preuzimanje opisa stranice");
        return getText(PAGE_DESCRIPTION);
    }

    /**
     * Proverava da li je naslov vidljiv
     */
    public boolean isTitleVisible() {
        logger.debug("Provera vidljivosti naslova");
        return isElementVisible(PAGE_TITLE);
    }

    /**
     * Verifikacija: naslov je vidljiv (asertacija)
     */
    public LandingPage verifyTitleVisible() {
        logger.info("Verifikacija vidljivosti naslova");
        assertTrue(isTitleVisible(), "Naslov (PAGE_TITLE) nije vidljiv na Landing Page.");
        return this;
    }

    /**
     * Proverava da li je opis vidljiv
     */
    public boolean isDescriptionVisible() {
        logger.debug("Provera vidljivosti opisa");
        return isElementVisible(PAGE_DESCRIPTION);
    }

    /**
     * Verifikacija: opis je vidljiv (asertacija)
     */
    public LandingPage verifyDescriptionVisible() {
        logger.info("Verifikacija vidljivosti opisa");
        assertTrue(isDescriptionVisible(), "Opis (PAGE_DESCRIPTION) nije vidljiv na Landing Page.");
        return this;
    }

    // ==================== LET'S START DUGME ====================

    /**
     * Klikće na "Let's Start" dugme
     */
    public ArticlesPage clickLetsStartButton() {
        logger.info("Klik na 'Let's Start' dugme");
        click(LETS_START_BUTTON);
        waitForPageLoad();
        return new ArticlesPage(driver);
    }

    /**
     * Proverava da li je "Let's Start" dugme vidljivo
     */
    public boolean isLetsStartButtonVisible() {
        logger.debug("Provera vidljivosti 'Let's Start' dugmeta");
        return isElementVisible(LETS_START_BUTTON);
    }

    /**
     * Proverava da li je "Let's Start" dugme dostupno
     */
    public boolean isLetsStartButtonAvailable() {
        logger.debug("Provera dostupnosti 'Let's Start' dugmeta");
        return isElementPresent(LETS_START_BUTTON);
    }

    /**
     * Verifikacija: 'Let's Start' dugme je vidljivo (asertacija)
     */
    public LandingPage verifyLetsStartButtonVisible() {
        logger.info("Verifikacija vidljivosti 'Let's Start' dugmeta");
        assertTrue(isLetsStartButtonVisible(), "'Let's Start' dugme nije vidljivo.");
        return this;
    }

    /**
     * Verifikacija: 'Let's Start' dugme je dostupno (asertacija)
     */
    public LandingPage verifyLetsStartButtonAvailable() {
        logger.info("Verifikacija dostupnosti 'Let's Start' dugmeta");
        assertTrue(isLetsStartButtonAvailable(), "'Let's Start' dugme nije dostupno (nije prisutno u DOM-u).");
        return this;
    }

    /**
     * Dobija tekst "Let's Start" dugmeta
     */
    public String getLetsStartButtonText() {
        logger.debug("Preuzimanje teksta 'Let's Start' dugmeta");
        return getText(LETS_START_BUTTON);
    }

    // ==================== FOOTER ====================

    /**
     * Dobija verziju iz footer-a
     */
    public String getFooterVersion() {
        logger.debug("Preuzimanje verzije iz footer-a");
        try {
            return getText(FOOTER_VERSION);
        } catch (Exception e) {
            logger.warn("Verzija nije dostupna", e);
            return "";
        }
    }

    /**
     * Proverava da li je footer vidljiv
     */
    public boolean isFooterVisible() {
        logger.debug("Provera vidljivosti footer-a");
        return isElementPresent(FOOTER_VERSION) || isElementPresent(FOOTER_COPYRIGHT);
    }

    /**
     * Verifikacija: footer je vidljiv (asertacija)
     */
    public LandingPage verifyFooterVisible() {
        logger.info("Verifikacija vidljivosti footer-a");
        assertTrue(isFooterVisible(), "Footer nije vidljiv (nema verzije i/ili copyright-a).");
        return this;
    }

    /**
     * Klikće na footer link
     */
    public LandingPage clickFooterLink() {
        logger.info("Klik na footer link");
        if (isElementPresent(FOOTER_LINK)) {
            click(FOOTER_LINK);
        } else {
            logger.warn("Footer link nije dostupan");
        }
        return this;
    }

    /**
     * Dobija URL footer linka
     */
    public String getFooterLinkUrl() {
        logger.debug("Preuzimanje URL-a footer linka");
        if (isElementPresent(FOOTER_LINK)) {
            return getAttribute(FOOTER_LINK, "href");
        }
        return "";
    }

    // ==================== TEME ====================

    /**
     * Proverava da li su theme dugmići dostupni
     */
    public boolean areThemeButtonsAvailable() {
        logger.debug("Provera dostupnosti theme dugmića");
        return isElementPresent(THEME_BUTTONS);
    }

    /**
     * Dobija broj theme dugmića
     */
    public int getThemeButtonsCount() {
        logger.debug("Preuzimanje broja theme dugmića");
        int count = findElements(THEME_BUTTONS).size();
        logger.info("Broj theme dugmića: {}", count);
        return count;
    }

    /**
     * Klikće na prvi theme dugme
     */
    public LandingPage clickFirstThemeButton() {
        logger.info("Klik na prvi theme dugme");
        if (isElementPresent(THEME_BUTTONS)) {
            click(THEME_BUTTONS);
        } else {
            logger.warn("Theme dugmići nisu dostupni");
        }
        return this;
    }

    // ==================== STANJA ====================

    /**
     * Čeka da se Landing Page učita
     */
    public LandingPage waitForLandingPageToLoad() {
        logger.info("Čekanje da se Landing Page učita");
        waitForElementToBeVisible(PAGE_TITLE);
        waitForElementToBeVisible(LETS_START_BUTTON);
        return this;
    }

    /**
     * Proverava da li je stranica kompletan učitana
     */
    public boolean isPageFullyLoaded() {
        logger.debug("Provera da li je Landing Page kompletan učitan");
        boolean hasTitle = isElementVisible(PAGE_TITLE);
        boolean hasDescription = isElementVisible(PAGE_DESCRIPTION);
        boolean hasButton = isElementVisible(LETS_START_BUTTON);
        boolean hasFooter = isFooterVisible();
        boolean isLoaded = hasTitle && hasDescription && hasButton && hasFooter;
        logger.info("Landing Page je {}kompletan učitan", isLoaded ? "" : "ni");
        return isLoaded;
    }

    /**
     * Verifikacija: Landing Page je kompletno učitan (asertacija)
     */
    public LandingPage verifyPageFullyLoaded() {
        logger.info("Verifikacija da je Landing Page kompletno učitana");
        assertTrue(isPageFullyLoaded(), "Landing Page nije kompletno učitana.");
        return this;
    }
}