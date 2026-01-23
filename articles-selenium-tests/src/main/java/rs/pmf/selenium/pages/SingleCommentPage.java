package rs.pmf.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rs.pmf.selenium.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Object klasa za stranicu sa detaljima pojedinačnog komentara (Single Comment)
 */
public class SingleCommentPage extends BasePage {

    // ==================== LOCATORS ====================

    // Navigacija
    private static final By RETURN_TO_COMMENTS_LINK = By.xpath("//a[contains(text(), 'Return to') or contains(text(), 'Back')]");
    private static final By RETURN_TO_ARTICLE_LINK = By.xpath("//a[contains(text(), 'Return to Article')]");
    
    // Komentar - Osnovni podaci
    private static final By COMMENT_ID = By.xpath(".//*[contains(text(), 'id:')]/following-sibling::*");
    private static final By COMMENT_AUTHOR = By.xpath(".//*[contains(text(), 'user:')]/following-sibling::a");
    private static final By COMMENT_DATE = By.xpath(".//*[contains(text(), 'date:')]/following-sibling::*");
    
    // Komentar - Tekst
    private static final By COMMENT_TEXT = By.xpath("//div[contains(@class, 'comment-text') or contains(@class, 'comment-content')]");
    private static final By COMMENT_CONTENT = By.xpath(".//*[contains(text(), 'comment:')]/following-sibling::p | //p[contains(@class, 'comment')]");
    
    // Članak na kojem je komentar
    private static final By ARTICLE_INFO_SECTION = By.xpath(".//*[contains(text(), 'article:')]/parent::*");
    private static final By ARTICLE_TITLE = By.xpath(".//*[contains(text(), 'article:')]/following-sibling::a");
    private static final By ARTICLE_LINK = By.xpath(".//*[contains(text(), 'article:')]/following-sibling::a");
    
    /**
     * Konstruktor
     */
    public SingleCommentPage(WebDriver driver) {
        super(driver);
    }
    
    // ==================== NAVIGATION ====================
    
    /**
     * Proverava da li je korisnik na SingleComment stranici
     */
    public boolean isOnSingleCommentPage() {
        logger.debug("Provera da li je korisnik na SingleComment stranici");
        boolean hasAuthor = isElementPresent(COMMENT_AUTHOR);
        boolean hasText = isElementPresent(COMMENT_CONTENT);
        boolean isOn = hasAuthor && hasText;
        logger.info("Korisnik je {}na SingleComment stranici", isOn ? "" : "ni ");
        return isOn;
    }

    /**
     * Verifikacija: korisnik je na SingleComment stranici (asertacija)
     */
    public SingleCommentPage verifyOnSingleCommentPage() {
        logger.info("Verifikacija da je korisnik na SingleComment stranici");
        assertTrue(isOnSingleCommentPage(), "Očekivano je da je korisnik na SingleComment stranici, ali nije.");
        return this;
    }

    /**
     * Vraća se na stranicu sa komentara
     */
    public CommentsPage returnToComments() {
        logger.info("Povratak na stranicu sa komentara");
        click(RETURN_TO_COMMENTS_LINK);
        waitForPageLoad();
        return new CommentsPage(driver);
    }

    /**
     * Vraća se na stranicu sa članka
     */
    public SingleArticlePage returnToArticle() {
        logger.info("Povratak na stranicu sa članka");
        if (isElementPresent(RETURN_TO_ARTICLE_LINK)) {
            click(RETURN_TO_ARTICLE_LINK);
            waitForPageLoad();
        } else {
            logger.warn("Link za povratak na članak nije dostupan");
        }
        return new SingleArticlePage(driver);
    }

    // ==================== METADATA ====================

    /**
     * Dobija ID komentara
     */
    public String getCommentId() {
        logger.debug("Preuzimanje ID-a komentara");
        return getText(COMMENT_ID);
    }

    /**
     * Dobija autora komentara
     */
    public String getCommentAuthor() {
        logger.debug("Preuzimanje autora komentara");
        return getText(COMMENT_AUTHOR);
    }

    /**
     * Dobija datum komentara
     */
    public String getCommentDate() {
        logger.debug("Preuzimanje datuma komentara");
        return getText(COMMENT_DATE);
    }

    // ==================== TEKST KOMENTARA ====================

    /**
     * Dobija tekst komentara
     */
    public String getCommentText() {
        logger.debug("Preuzimanje teksta komentara");
        return getText(COMMENT_CONTENT);
    }

    /**
     * Proverava da li tekst komentara sadrži određeni tekst
     */
    public boolean isCommentTextContains(String text) {
        logger.debug("Provera da li tekst komentara sadrži: {}", text);
        String commentText = getCommentText();
        boolean contains = commentText.contains(text);
        logger.info("Tekst komentara {}sadrži '{}'", contains ? "" : "ne ", text);
        return contains;
    }

    /**
     * Dobija dužinu teksta komentara (broj karaktera)
     */
    public int getCommentTextLength() {
        logger.debug("Preuzimanje dužine teksta komentara");
        int length = getCommentText().length();
        logger.info("Dužina teksta komentara: {} karaktera", length);
        return length;
    }

    // ==================== ČLANAK ====================

    /**
     * Dobija naslov članka na kojem je komentar
     */
    public String getArticleTitle() {
        logger.debug("Preuzimanje naslova članka");
        return getText(ARTICLE_TITLE);
    }

    /**
     * Proverava da li je informacija o članku vidljiva
     */
    public boolean isArticleInfoVisible() {
        logger.debug("Provera vidljivosti informacija o članku");
        boolean isVisible = isElementPresent(ARTICLE_INFO_SECTION);
        logger.info("Informacija o članku je {}vidljiva", isVisible ? "" : "ni");
        return isVisible;
    }

    /**
     * Klikće na link članka da otvori članak
     */
    public SingleArticlePage clickArticleLink() {
        logger.info("Klik na link članka");
        click(ARTICLE_LINK);
        waitForPageLoad();
        return new SingleArticlePage(driver);
    }

    /**
     * Proverava da li je link članka dostupan
     */
    public boolean isArticleLinkAvailable() {
        logger.debug("Provera dostupnosti linka na članak");
        return isElementPresent(ARTICLE_LINK);
    }

    /**
     * Verifikacija: link ka članku je dostupan (asertacija)
     */
    public SingleCommentPage verifyArticleLinkAvailable() {
        logger.info("Verifikacija dostupnosti linka na članak");
        assertTrue(isArticleLinkAvailable(), "Link na članak nije dostupan.");
        return this;
    }

    // ==================== STANJA ====================

    /**
     * Čeka da se komentar učita
     */
    public SingleCommentPage waitForCommentToLoad() {
        logger.info("Čekanje da se komentar učita");
        waitForElementToBeVisible(COMMENT_AUTHOR);
        waitForElementToBeVisible(COMMENT_CONTENT);
        return this;
    }


    /**
     * Proverava da li je komentar kompletan učitan
     */
    public boolean isCommentFullyLoaded() {
        logger.debug("Provera da li je komentar kompletan učitan");
        boolean hasAuthor = isElementVisible(COMMENT_AUTHOR);
        boolean hasDate = isElementVisible(COMMENT_DATE);
        boolean hasText = isElementVisible(COMMENT_CONTENT);
        boolean hasArticleInfo = isElementPresent(ARTICLE_INFO_SECTION);
        boolean isLoaded = hasAuthor && hasDate && hasText && hasArticleInfo;
        logger.info("Komentar je {}kompletan učitan", isLoaded ? "" : "ni");
        return isLoaded;
    }

    /**
     * Verifikacija: komentar je kompletno učitan (asertacija)
     */
    public SingleCommentPage verifyCommentFullyLoaded() {
        logger.info("Verifikacija da je komentar kompletno učitan");
        assertTrue(isCommentFullyLoaded(), "Komentar nije kompletno učitan.");
        return this;
    }

    /**
     * Skroluje do teksta komentara
     */
    public SingleCommentPage scrollToCommentText() {
        logger.info("Skrolovanje do teksta komentara");
        scrollToElement(COMMENT_CONTENT);
        return this;
    }

    /**
     * Proverava da li je autor komentara link
     */
    public boolean isAuthorLink() {
        logger.debug("Provera da li je autor link");
        boolean isLink = isElementPresent(COMMENT_AUTHOR);
        logger.info("Autor {}jeste link", isLink ? "" : "ni");
        return isLink;
    }
}
