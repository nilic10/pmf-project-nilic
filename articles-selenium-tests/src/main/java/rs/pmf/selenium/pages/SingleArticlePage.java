package rs.pmf.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rs.pmf.selenium.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Object klasa za stranicu sa detaljima pojedinačnog članka (Single Article)
 */
public class SingleArticlePage extends BasePage {

    // ==================== LOCATORS ====================

    // Navigacija
    private static final By RETURN_TO_ARTICLES_LINK = By.xpath("//a[contains(text(), 'Return to') or contains(text(), 'Back')]");

    // Članak - Naslov i osnovni podaci
    private static final By ARTICLE_TITLE = By.xpath("//h1 | //h2[contains(@class, 'title')]");
    private static final By ARTICLE_IMAGE = By.xpath("//img[contains(@class, 'article-image')]");

    // Metadata članka
    private static final By ARTICLE_ID = By.xpath(".//*[contains(text(), 'id:')]/following-sibling::*");
    private static final By ARTICLE_AUTHOR = By.xpath(".//*[contains(text(), 'author:')]/following-sibling::a | .//*[contains(text(), 'user:')]/following-sibling::a");
    private static final By ARTICLE_DATE = By.xpath(".//*[contains(text(), 'date:')]/following-sibling::*");

    // Sadržaj članka
    private static final By ARTICLE_CONTENT = By.xpath("//div[contains(@class, 'article-content') or contains(@class, 'content')]//p");
    private static final By ARTICLE_FULL_TEXT = By.xpath("//div[contains(@class, 'article-content') or contains(@class, 'content')]");

    // Download opcije
    private static final By DOWNLOAD_CSV_BUTTON = By.xpath("//button[contains(text(), 'CSV')] | //a[contains(text(), 'CSV')]");
    private static final By DOWNLOAD_JSON_BUTTON = By.xpath("//button[contains(text(), 'JSON')] | //a[contains(text(), 'JSON')]");
    private static final By DOWNLOAD_PDF_BUTTON = By.xpath("//button[contains(text(), 'PDF')] | //a[contains(text(), 'PDF')]");
    private static final By DOWNLOAD_CONTAINER = By.xpath("//*[contains(text(), 'Download article as:')]/parent::*");

    /**
     * Konstruktor
     */
    public SingleArticlePage(WebDriver driver) {
        super(driver);
    }

    // ==================== NAVIGATION ====================

    /**
     * Proverava da li je korisnik na SingleArticle stranici
     */
    public boolean isOnSingleArticlePage() {
        logger.debug("Provera da li je korisnik na SingleArticle stranici");
        boolean hasTitle = isElementPresent(ARTICLE_TITLE);
        boolean hasContent = isElementPresent(ARTICLE_FULL_TEXT);
        boolean isOn = hasTitle && hasContent;
        logger.info("Korisnik je {}na SingleArticle stranici", isOn ? "" : "ni ");
        return isOn;
    }

    /**
     * Vraća se na stranicu sa članaka
     */
    public ArticlesPage returnToArticles() {
        logger.info("Povratak na stranicu sa članaka");
        click(RETURN_TO_ARTICLES_LINK);
        waitForPageLoad();
        return new ArticlesPage(driver);
    }

    /**
     * Verifikacija: korisnik je na SingleArticle stranici (asertacija)
     */
    public SingleArticlePage verifyOnSingleArticlePage() {
        logger.info("Verifikacija da je korisnik na SingleArticle stranici");
        assertTrue(isOnSingleArticlePage(), "Očekivano je da je korisnik na SingleArticle stranici, ali nije.");
        return this;
    }

    /**
     * Proverava da li je link za povratak dostupan
     */
    public boolean isReturnLinkAvailable() {
        logger.debug("Provera dostupnosti linka za povratak");
        return isElementPresent(RETURN_TO_ARTICLES_LINK);
    }

    // ==================== NASLOV I SLIKA ====================

    /**
     * Dobija naslov članka
     */
    public String getArticleTitle() {
        logger.debug("Preuzimanje naslova članka");
        return getText(ARTICLE_TITLE);
    }

    /**
     * Proverava da li je slika članka vidljiva
     */
    public boolean isArticleImageVisible() {
        logger.debug("Provera vidljivosti slike članka");
        boolean isVisible = isElementPresent(ARTICLE_IMAGE);
        logger.info("Slika članka je {}vidljiva", isVisible ? "" : "ni");
        return isVisible;
    }

    /**
     * Dobija atribut slike (src, alt, itd.)
     */
    public String getArticleImageAttribute(String attributeName) {
        logger.debug("Preuzimanje atributa '{}' slike članka", attributeName);
        return getAttribute(ARTICLE_IMAGE, attributeName);
    }

    // ==================== METADATA ====================

    /**
     * Dobija ID članka
     */
    public String getArticleId() {
        logger.debug("Preuzimanje ID-a članka");
        return getText(ARTICLE_ID);
    }

    /**
     * Dobija autora članka
     */
    public String getArticleAuthor() {
        logger.debug("Preuzimanje autora članka");
        return getText(ARTICLE_AUTHOR);
    }

    /**
     * Dobija datum objave članka
     */
    public String getArticleDate() {
        logger.debug("Preuzimanje datuma objave članka");
        return getText(ARTICLE_DATE);
    }

    // ==================== SADRŽAJ ====================

    /**
     * Dobija kompletan tekst članka
     */
    public String getArticleContent() {
        logger.debug("Preuzimanje kompletan teksta članka");
        return getText(ARTICLE_FULL_TEXT);
    }

    /**
     * Proverava da li sadržaj članka sadrži određeni tekst
     */
    public boolean isContentContains(String text) {
        logger.debug("Provera da li sadržaj sadrži tekst: {}", text);
        String content = getArticleContent();
        boolean contains = content.contains(text);
        logger.info("Sadržaj {}sadrži tekst '{}'", contains ? "" : "ne ", text);
        return contains;
    }

    /**
     * Dobija broj paragrafa u članku
     */
    public int getContentParagraphsCount() {
        logger.debug("Preuzimanje broja paragrafa");
        int count = findElements(ARTICLE_CONTENT).size();
        logger.info("Broj paragrafa: {}", count);
        return count;
    }

    // ==================== DOWNLOAD ====================

    /**
     * Proverava da li je download sekcija vidljiva
     */
    public boolean isDownloadSectionVisible() {
        logger.debug("Provera vidljivosti download sekcije");
        boolean isVisible = isElementPresent(DOWNLOAD_CONTAINER);
        logger.info("Download sekcija je {}vidljiva", isVisible ? "" : "ni");
        return isVisible;
    }

    /**
     * Proverava da li je CSV download opcija dostupna
     */
    public boolean isCSVDownloadAvailable() {
        logger.debug("Provera dostupnosti CSV download opcije");
        return isElementPresent(DOWNLOAD_CSV_BUTTON);
    }

    /**
     * Proverava da li je JSON download opcija dostupna
     */
    public boolean isJSONDownloadAvailable() {
        logger.debug("Provera dostupnosti JSON download opcije");
        return isElementPresent(DOWNLOAD_JSON_BUTTON);
    }

    /**
     * Proverava da li je PDF download opcija dostupna
     */
    public boolean isPDFDownloadAvailable() {
        logger.debug("Provera dostupnosti PDF download opcije");
        return isElementPresent(DOWNLOAD_PDF_BUTTON);
    }

    /**
     * Klikće na CSV download dugme
     */
    public SingleArticlePage downloadAsCSV() {
        logger.info("Download članka kao CSV");
        click(DOWNLOAD_CSV_BUTTON);
        return this;
    }

    /**
     * Klikće na JSON download dugme
     */
    public SingleArticlePage downloadAsJSON() {
        logger.info("Download članka kao JSON");
        click(DOWNLOAD_JSON_BUTTON);
        return this;
    }

    /**
     * Klikće na PDF download dugme
     */
    public SingleArticlePage downloadAsPDF() {
        logger.info("Download članka kao PDF");
        click(DOWNLOAD_PDF_BUTTON);
        return this;
    }

    // ==================== STANJA ====================

    /**
     * Čeka da se članak učita
     */
    public SingleArticlePage waitForArticleToLoad() {
        logger.info("Čekanje da se članak učita");
        waitForElementToBeVisible(ARTICLE_TITLE);
        waitForElementToBeVisible(ARTICLE_FULL_TEXT);
        return this;
    }

    /**
     * Proverava da li je članak kompletan učitan
     */
    public boolean isArticleFullyLoaded() {
        logger.debug("Provera da li je članak kompletan učitan");
        boolean hasTitle = isElementVisible(ARTICLE_TITLE);
        boolean hasContent = isElementVisible(ARTICLE_FULL_TEXT);
        boolean hasMetadata = isElementPresent(ARTICLE_AUTHOR) && isElementPresent(ARTICLE_DATE);
        boolean isLoaded = hasTitle && hasContent && hasMetadata;
        logger.info("Članak je {}kompletan učitan", isLoaded ? "" : "ni");
        return isLoaded;
    }

    /**
     * Verifikacija: članak je kompletno učitan (asertacija)
     */
    public SingleArticlePage verifyArticleFullyLoaded() {
        logger.info("Verifikacija da je članak kompletno učitan");
        assertTrue(isArticleFullyLoaded(), "Članak nije kompletno učitan.");
        return this;
    }

    /**
     * Verifikacija: download sekcija je vidljiva (asertacija)
     */
    public SingleArticlePage verifyDownloadSectionVisible() {
        logger.info("Verifikacija vidljivosti download sekcije");
        assertTrue(isDownloadSectionVisible(), "Download sekcija nije vidljiva.");
        return this;
    }

    /**
     * Skroluje do određenog dela članka
     */
    public SingleArticlePage scrollToContent() {
        logger.info("Skrolovanje do sadržaja članka");
        scrollToElement(ARTICLE_FULL_TEXT);
        return this;
    }
}
